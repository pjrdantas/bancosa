package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbClienteDTO;





public interface ITbClienteService {
	
    void addTbClienteDTO(TbClienteDTO tbClienteDTO)  throws Exception, Throwable ;
    void updateTbClienteDTO(TbClienteDTO tbClienteDTO)  throws Exception, Throwable ;	
    List<TbClienteDTO> consultar()  throws Exception, Throwable ;
    TbClienteDTO getTbClienteById(int id)  throws Exception, Throwable ;
    void deleteTbCliente(int id)  throws Exception, Throwable ;
}
