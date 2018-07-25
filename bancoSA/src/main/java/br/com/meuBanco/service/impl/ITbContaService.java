package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbContaDTO;





public interface ITbContaService {
	
	/**
	 * 
	 * @param tbContaDTO
	 * @throws Exception
	 * @throws Throwable
	 */
    void addTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @param tbContaDTO
     * @throws Exception
     * @throws Throwable
     */
    void updateTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<TbContaDTO> consultar()  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     * @throws Throwable
     */
    TbContaDTO getTbContaById(int id)  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     * @throws Throwable
     */
    TbContaDTO getTbContaByCliente(int id)  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @throws Exception
     * @throws Throwable
     */
    void deleteTbConta(int id)  throws Exception, Throwable;
}
