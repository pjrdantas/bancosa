package br.com.meuBanco.service;

import java.util.List;

import br.com.meuBanco.dto.ClienteDTO;





public interface ClienteService {
	
	/**
	 * 
	 * @param tbClienteDTO
	 * @throws Exception
	 * @throws Throwable
	 */
    void addCliente(ClienteDTO clienteDTO)  throws Exception, Throwable ;
    
    /**
     * 
     * @param tbClienteDTO
     * @throws Exception
     * @throws Throwable
     */
    void updateCliente(ClienteDTO clienteDTO)  throws Exception, Throwable ;
    
    /**
     * 
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<ClienteDTO> getAllClientes()  throws Exception, Throwable ;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     * @throws Throwable
     */
    ClienteDTO getClienteById(int id)  throws Exception, Throwable ;
    
    /**
     * 
     * @param id
     * @throws Exception
     * @throws Throwable
     */
    void deleteCliente(int id)  throws Exception, Throwable ;
}
