package com.preorder.global.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    LOGIN_FAIL("로그인 실패"),
    SUCCESS_CODE("성공");

    private final String description;
}
