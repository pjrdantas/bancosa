package br.com.meuBanco.repository;


import java.util.List;

import br.com.meuBanco.dto.ContaDTO;





public interface ContaRepository {
	
    /**
     * 
     * @param tbContaDTO
     * @throws Exception
     * @throws Throwable
     */
    void addConta(ContaDTO contaDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @param tbContaDTO
     * @throws Exception
     * @throws Throwable
     */
    void updateConta(ContaDTO contaDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<ContaDTO> getAllContas()  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     * @throws Throwable
     */
    ContaDTO getContaById(int id)  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     * @throws Throwable
     */
    ContaDTO getContaByCliente(int id)  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @throws Exception
     * @throws Throwable
     */
    void deleteConta(int id)  throws Exception, Throwable;
    
    
   
}
 