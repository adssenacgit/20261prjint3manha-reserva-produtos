package br.com.senac.prjint3.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErroApi(
        LocalDateTime dataHora,
        int status,
        String erro,
        List<String> mensagens
) {
}
