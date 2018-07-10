package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.TbCliente;
import br.com.meuBanco.entity.dto.TbClienteDTO;



public interface ItbClienteDAO {
	
    
    void addTbCliente(TbCliente tbCliente);
    void updateTbCliente(TbCliente tbCliente);
    List<TbClienteDTO> getAllTbClientes();
    TbClienteDTO getTbClienteById(int id);
    void deleteTbCliente(int id);
    
    
   
}
 