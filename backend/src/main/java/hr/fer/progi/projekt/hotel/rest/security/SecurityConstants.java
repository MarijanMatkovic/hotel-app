package hr.fer.progi.projekt.hotel.rest.security;

public abstract class SecurityConstants {
    public static final String SECRET = "TajnaBatine";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final Long EXPIRATION_TIME = 10 * 24 * 60 * 60 * 1000L;
}
