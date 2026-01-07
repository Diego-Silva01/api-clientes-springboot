    package com.MinhaAPIclientes.APIClentes.exception;

    public class FieldErrorResponse {

        private String campo;
        private String mensagem;

        public FieldErrorResponse(String campo, String mensagem) {
            this.campo = campo;
            this.mensagem = mensagem;
        }

        public String getCampo() {
            return campo;
        }

        public String getMensagem() {
            return mensagem;
        }
    }
