package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.dto.TbNotaDTO;



public interface ItbNotaDAO {
	
    
	void addTbNotaDTO(TbNotaDTO tbNotaDTO)  throws Exception, Throwable;
    void updateTbNotaDTO(TbNotaDTO tbNotaDTO)  throws Exception, Throwable;
    List<TbNotaDTO> getAllTbNotas()  throws Exception, Throwable;
    TbNotaDTO getTbNotaById(int id)  throws Exception, Throwable;
    void deleteTbNota(int id)  throws Exception, Throwable;
    
    
   
}
 