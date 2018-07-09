package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.TbNota;



public interface ItbNotaDAO {
	
    
    void addTbNota(TbNota tbNota);
    void updateTbNota(TbNota tbNota);
    List<TbNota> getAllTbNotas();
    TbNota getTbNotaById(int id);
    void deleteTbNota(int id);
    
    
   
}
 