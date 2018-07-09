package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.TbAgencia;
import br.com.meuBanco.entity.dto.TbAgenciaDTO;



public interface ItbAgenciaDAO {
	
    
    void addTbAgencia(TbAgencia tbAgencia);
    void updateTbAgencia(TbAgencia tbAgencia);
    List<TbAgenciaDTO> getAllTbAgencias();
    TbAgenciaDTO getTbAgenciaById(int id);
    void deleteTbAgencia(int id);
    
    
   
}
 