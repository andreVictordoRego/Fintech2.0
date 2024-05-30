package br.com.fiap.fintech.bean;

import java.io.File;

import java.time.LocalDate;
import br.com.fiap.fintech.util.CriptografiaUtils;

public class Usuario {

	private Integer numeroDeCPF;
	private String nomeCompleto;
	private LocalDate dataDeNascimento;
	private String genero;
	private String email;
	private File imagemFoto;
	private String senha;
	
	
	
	public Usuario() {
		super();
	}

	public Usuario(Integer numeroDeCPF, String nomeCompleto, LocalDate dataDeNascimento, String genero, String email,
			File imagemFoto, String senha) {
		super();
		this.numeroDeCPF = numeroDeCPF;
		this.nomeCompleto = nomeCompleto;
		this.dataDeNascimento = dataDeNascimento;
		this.genero = genero;
		this.email = email;
		this.imagemFoto = imagemFoto;
		setSenha(senha);
	}

	public Integer getNumeroDeCPF() {
		return numeroDeCPF;
	}

	public void setNumeroDeCPF(Integer numeroDeCPF) {
		this.numeroDeCPF = numeroDeCPF;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public File getImagemFoto() {
		return imagemFoto;
	}

	public void setImagemFoto(File imagemFoto) {
		this.imagemFoto = imagemFoto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		
		try { 
			
			this.senha = CriptografiaUtils.criptografar(senha);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
