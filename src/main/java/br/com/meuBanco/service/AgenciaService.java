package br.com.meuBanco.service;

import java.util.List;

import br.com.meuBanco.dto.AgenciaDTO;





public interface AgenciaService {
	
	/**
	 * 
	 * @param tbAgenciaDTO
	 * @throws Exception
	 * @throws Throwable
	 */
    void addAgencia(AgenciaDTO agenciaDTO)   throws Exception, Throwable;
    
    /**
     * 
     * @param tbAgenciaDTO
     * @throws Exception
     * @throws Throwable
     */
    void updateAgencia(AgenciaDTO agenciaDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<AgenciaDTO> getAllAgencias()  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     * @throws Throwable
     */
    AgenciaDTO getAgenciaById(int id)  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @throws Exception
     * @throws Throwable
     */
    void deleteAgencia(int id)  throws Exception, Throwable;
}
