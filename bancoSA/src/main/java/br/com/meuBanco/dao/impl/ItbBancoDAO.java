package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.TbBanco;
import br.com.meuBanco.entity.dto.TbBancoDTO;



public interface ItbBancoDAO {
	
    
    void addTbBanco(TbBanco tbBanco);
    void updateTbBanco(TbBanco tbBanco);
    List<TbBancoDTO> getAllTbBancos();
    TbBancoDTO getTbBancoById(int id);
    void deleteTbBanco(int id);
    
    
   
}
 