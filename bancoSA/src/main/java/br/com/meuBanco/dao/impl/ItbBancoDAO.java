package br.com.meuBanco.dao.impl;


import java.util.List;
import br.com.meuBanco.entity.TbBanco;


public interface ItbBancoDAO {
	
    
    void addTbBanco(TbBanco tbBanco);
    void updateTbBanco(TbBanco tbBanco);
    List<TbBanco> getAllTbBancos();
    TbBanco getTbBancoById(int id);
    void deleteTbBanco(int id);
    
    
   
}
 