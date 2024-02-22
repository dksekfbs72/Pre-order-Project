import requests
from concurrent.futures import ThreadPoolExecutor
import time

def send_http_request(url):
    try:
        start_time = time.time()  # 요청 시작 시간 기록
        response = requests.put(url)
        end_time = time.time()  # 요청 완료 시간 기록
        print(f"Request to {url} completed with status code {response.status_code}. Time taken: {end_time - start_time:.2f} seconds")
    except Exception as e:
        print(f"Error sending request to {url}: {e}")

def main():
    # Set the number of concurrent requests (N)
    num_requests = 500

    # Base URL
    base_url = "http://host.docker.internal:8083/activity/order"

    # Create a ThreadPoolExecutor to send concurrent requests
    with ThreadPoolExecutor(max_workers=num_requests) as executor:
        # Use a list comprehension to create a list of tasks
        tasks = [executor.submit(send_http_request, f"{base_url}?orderId={order_id}") for order_id in range(1, num_requests + 1)]

        # Wait for all tasks to complete
        for future in tasks:
            future.result()

if __name__ == "__main__":
    start_time = time.time()  # 테스트 시작 시간 기록
    main()
    end_time = time.time()  # 테스트 완료 시간 기록
    print(f"All requests completed. Total time taken: {end_time - start_time:.2f} seconds")
