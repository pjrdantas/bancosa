package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbNota;




public interface ITbNotaService {
	
    void addTbNota(TbNota tbNota);
    void updateTbNota(TbNota tbNota);	
    List<TbNota> consultar();
    TbNota getTbNotaById(int id);
    void deleteTbNota(int id);
}
