package br.com.meuBanco.service;

import java.util.List;

import br.com.meuBanco.dto.BancoDTO;





public interface BancoService {
	
	/**
	 * 
	 * @param tbBancoDTO
	 * @throws Exception
	 * @throws Throwable
	 */
    void addBanco(BancoDTO bancoDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @param tbBancoDTO
     * @throws Exception
     * @throws Throwable
     */
    void updateBanco(BancoDTO bancoDTO)  throws Exception, Throwable;
    
    /**
     * 
     * @return
     * @throws Exception
     * @throws Throwable
     */
	List<BancoDTO> getAllBancos()  throws Exception, Throwable;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
    BancoDTO getBancoById(int id)  throws Exception, Throwable;
    
    /**
     * 
     * @param id
     * @throws Exception
     * @throws Throwable
     */
    void deleteBanco(int id)  throws Exception, Throwable;
}
