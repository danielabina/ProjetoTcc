package com.example.vamosjogarv1.model;

/**
 * Classe de atributos CLIENTE
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Cliente{

        public int  idCliente;
        public String cnpj;

        public int getIdCliente() {
                return idCliente;
        }

        public void setIdCliente(int idCliente) {
                this.idCliente = idCliente;
        }

        public String getCnpj() {
                return cnpj;
        }

        public void setCnpj(String cnpj) {
                this.cnpj = cnpj;
        }
}