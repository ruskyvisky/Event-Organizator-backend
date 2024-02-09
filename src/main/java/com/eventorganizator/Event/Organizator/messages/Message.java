package com.eventorganizator.Event.Organizator.messages;

import lombok.Getter;

public enum Message {
    /** GENERAL */
    SUCCESS("SUCCESS", "İşlem Başarılı"),
    BAD_REQUEST("BAD_REQUEST", "Hatalı istek."),
    FORBIDDEN("FORBIDDEN", "Erişim engellendi."),
    UNAUTHORIZED("UNAUTHORIZED", "Yetkisiz giriş."),
    NOT_FOUND("NOT_FOUND", "Bulunamadı."),
    SYSTEM_ERROR("SYSTEM_ERROR", "Sistem hatası."),
    BAD_CREDENTIALS("BAD_CREDENTIALS", "E-posta adresi veya şifre hatalı.");

    private final String text;
    @Getter
    private final String desc;

    Message(final String text, final String desc) {
        this.text = text;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return text;
    }


}
