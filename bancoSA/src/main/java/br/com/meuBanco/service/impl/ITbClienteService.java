package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbCliente;
import br.com.meuBanco.entity.dto.TbClienteDTO;





public interface ITbClienteService {
	
    void addTbCliente(TbCliente tbCliente);
    void updateTbCliente(TbCliente tbCliente);	
    List<TbClienteDTO> consultar();
    TbClienteDTO getTbClienteById(int id);
    void deleteTbCliente(int id);
}
