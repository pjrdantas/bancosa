package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.dto.TbContaDTO;



public interface ItbContaDAO {
	
    
    void addTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable;
    void updateTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable;
    List<TbContaDTO> getAllTbContas()  throws Exception, Throwable;
    TbContaDTO getTbContaById(int id)  throws Exception, Throwable;
    void deleteTbConta(int id)  throws Exception, Throwable;
    
    
   
}
 