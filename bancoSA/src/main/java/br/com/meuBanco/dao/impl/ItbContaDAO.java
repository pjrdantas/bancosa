package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.TbConta;
import br.com.meuBanco.entity.dto.TbContaDTO;



public interface ItbContaDAO {
	
    
    void addTbConta(TbConta tbConta);
    void updateTbConta(TbConta tbConta);
    List<TbContaDTO> getAllTbContas();
    TbContaDTO getTbContaById(int id);
    void deleteTbConta(int id);
    
    
   
}
 