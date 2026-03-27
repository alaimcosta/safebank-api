package br.com.safebank.domain.enums;

public enum ErrorCode {
    REGRA_NEGOCIO("REGRA_NEGOCIO"),
    VALIDACAO("VALIDACAO"),
    RECURSO_NAO_ENCONTRADO("RECURSO_NAO_ENCONTRADO"),
    ERRO_INTERNO("ERRO_INTERNO");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
