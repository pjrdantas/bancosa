package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.TbAgencia;



public interface ItbAgenciaDAO {
	
    
    void addTbAgencia(TbAgencia tbAgencia);
    void updateTbAgencia(TbAgencia tbAgencia);
    List<TbAgencia> getAllTbAgencias();
    TbAgencia getTbAgenciaById(int id);
    void deleteTbAgencia(int id);
    
    
   
}
 