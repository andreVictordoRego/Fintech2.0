package br.com.fiap.fintech.dao;

import java.util.List; 

import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.exception.DBException;

public interface ObjetivoDAO {

	public void criarNovoObjetivo(Objetivo objetivo) throws DBException;
	public List<Objetivo> listarObjetivos(Long numeroDeCPF);
	public void editarObjetivo(Objetivo objetivo) throws DBException;
	public void excluirObjetivo(Integer codigoDoObjetivo) throws DBException;
}
