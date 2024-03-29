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
    BAD_CREDENTIALS("BAD_CREDENTIALS", "E-posta adresi veya şifre hatalı."),

    /** USER */
    USER_NOT_FOUND("USER_NOT_FOUND", "Kullanıcı bulunamadı."),

    USERNAME_EXISTS("USERNAME_EXISTS", "Kullanıcı adı zaten mevcut."),

    /** EVENT */
    EVENT_NOT_FOUND("EVENT_NOT_FOUND", "Etkinlik bulunamadı."),
    EVENT_ALREADY_JOINED("EVENT_ALREADY_JOINED", "Etkinliğe zaten katıldınız."),
    NOT_JOINED("NOT_JOINED", "Etkinliğe katılmadınız."),


    /** COMMENT */
    COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "Yorum bulunamadı."),
    COMMENTS_IS_EMPTY("COMMENTS_IS_EMPTY", "Bu etkinlikte yorum bulunmamaktadır.");


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
