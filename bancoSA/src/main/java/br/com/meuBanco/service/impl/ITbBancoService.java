package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbBancoDTO;





public interface ITbBancoService {
	
	/**
	 * 
	 * @param tbBancoDTO
	 * @throws Exception
	 * @throws Throwable
	 */
    void addTbBancoDTO(TbBancoDTO tbBancoDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @param tbBancoDTO
     * @throws Exception
     * @throws Throwable
     */
    void updateTbBancoDTO(TbBancoDTO tbBancoDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @return
     * @throws Exception
     * @throws Throwable
     */
	List<TbBancoDTO> getAllTbBancos()  throws Exception, Throwable;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
    TbBancoDTO getTbBancoById(int id)  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @throws Exception
     * @throws Throwable
     */
    void deleteTbBanco(int id)  throws Exception, Throwable;
}
