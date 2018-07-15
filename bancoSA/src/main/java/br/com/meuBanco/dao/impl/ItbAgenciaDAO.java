package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.dto.TbAgenciaDTO;



public interface ItbAgenciaDAO {
	
    
    void addTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable;
    void updateTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable;
    List<TbAgenciaDTO> getAllTbAgencias()  throws Exception, Throwable;
    TbAgenciaDTO getTbAgenciaById(int id)  throws Exception, Throwable;
    void deleteTbAgencia(int id)  throws Exception, Throwable;
    
    
   
}
 