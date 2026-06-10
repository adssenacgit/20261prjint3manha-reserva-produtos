package br.com.senac.prjint3.common;

public final class StatusRegistro {
    public static final int APAGADO = -1;
    public static final int INATIVO = 0;
    public static final int ATIVO = 1;

    private StatusRegistro() {
    }

    public static int normalizar(Integer status) {
        if (status == null) {
            return ATIVO;
        }
        validar(status);
        return status;
    }

    public static void validar(Integer status) {
        if (status == null || (status != APAGADO && status != INATIVO && status != ATIVO)) {
            throw new IllegalArgumentException("Status inválido. Use -1 para apagado, 0 para inativo ou 1 para ativo.");
        }
    }
}
