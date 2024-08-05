package kr.co.kyobongbook.common.infra.exception;

public class KyobongException extends RuntimeException {

    public KyobongException(Exception e) {
        super(e);
    }

    public KyobongException(String message) {
        super(message);
    }

}
