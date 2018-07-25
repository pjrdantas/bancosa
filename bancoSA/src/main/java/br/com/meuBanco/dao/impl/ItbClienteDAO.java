package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.dto.TbClienteDTO;



public interface ItbClienteDAO {
	
    /**
     * 
     * @param tbClienteDTO
     * @throws Exception
     * @throws Throwable
     */
    void addTbClienteDTO(TbClienteDTO tbClienteDTO)  throws Exception, Throwable ;
    
    /**
     * 
     * @param tbClienteDTO
     * @throws Exception
     * @throws Throwable
     */
    void updateTbClienteDTO(TbClienteDTO tbClienteDTO)  throws Exception, Throwable ;
    
    /**
     * 
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<TbClienteDTO> getAllTbClientes()  throws Exception, Throwable ;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     * @throws Throwable
     */
    TbClienteDTO getTbClienteById(int id)  throws Exception, Throwable ;
    
    /**
     * 
     * @param id
     * @throws Exception
     * @throws Throwable
     */
    void deleteTbCliente(int id)  throws Exception, Throwable ;
    
    
   
}
 