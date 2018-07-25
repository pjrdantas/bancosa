package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.dto.TbAgenciaDTO;



public interface ItbAgenciaDAO {
	
    /**
     * 
     * @param tbAgenciaDTO
     * @throws Exception
     * @throws Throwable
     */
    void addTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @param tbAgenciaDTO
     * @throws Exception
     * @throws Throwable
     */
    void updateTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<TbAgenciaDTO> getAllTbAgencias()  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     * @throws Throwable
     */
    TbAgenciaDTO getTbAgenciaById(int id)  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @throws Exception
     * @throws Throwable
     */
    void deleteTbAgencia(int id)  throws Exception, Throwable;
    
    
   
}
 