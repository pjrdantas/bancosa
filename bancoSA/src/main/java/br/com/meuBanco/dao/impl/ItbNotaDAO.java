package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.TbNota;
import br.com.meuBanco.entity.dto.TbNotaDTO;



public interface ItbNotaDAO {
	
    
    void addTbNota(TbNota tbNota);
    void updateTbNota(TbNota tbNota);
    List<TbNotaDTO> getAllTbNotas();
    TbNotaDTO getTbNotaById(int id);
    void deleteTbNota(int id);
    
    
   
}
 