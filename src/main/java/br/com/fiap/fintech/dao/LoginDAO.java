package br.com.fiap.fintech.dao;

public interface LoginDAO {

	void validarLogin(String email, String senha);
	void logarComGmail();
		
}
