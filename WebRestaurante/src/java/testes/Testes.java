/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import entity.Cliente;
import entity.ContaCliente;
import entity.ItemContaCli;
import entity.Mesa;
import entity.Produto;
import entity.Usuario;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jeanfernandes
 */
public class Testes {

    public static void main(String[] args) {
        insertProduto();
        insertUsuario();
        insertMesa();    
        insertCliente();
        insertContaCli();      
        //testeForeach();
        //testConsultaPorNome();

    }

    public static void testConsultaPorNome(){
        Produto p = new Produto();
        p = p.findByName("vai");
        
        Usuario u = new Usuario();
        u = u.findByName("novo");
    }
    
    public static void insertContaCli() {
        ContaCliente contacli = new ContaCliente();
        contacli.setDataAbertura(new Date());

        Usuario us = new Usuario();
        us = us.findByName("teste");
        
        Cliente cli = new Cliente();
        cli = cli.findByName("teste");
        
        
        Mesa mesa = new Mesa();
        mesa.setNrMesa(24);

        contacli.setMesa(mesa);

        ItemContaCli item = new ItemContaCli();
        ItemContaCli item1 = new ItemContaCli();
        Produto prod = new Produto();

        item.setProduto(prod.getProduto(901));

        item1.setProduto(prod.getProduto(601));
        contacli.add(item);
        contacli.add(item1);
        
        contacli.salvar();

    }

    public static void insertMesa() {
        Mesa mesa = new Mesa();

        List<?> list = mesa.getAll();

        mesa.setNrMesa(1);
        mesa.setAberta(true);
        mesa.salvar();
    }

    public static void insertProduto() {
        Produto p = new Produto();
        p.setMarca("teste");
        p.setNome("teste");
        p.salvar();
    }

    public static void insertUsuario() {
        Usuario u = new Usuario();
        u.setEndereço("tal");
        u.setNome("teste");
        u.setSexo("fem");
        u.salvar();
    }
    
    public static void insertCliente(){
        Cliente c = new Cliente();
        c.setNome("teste");
        c.setIdade(21);
        c.setSexo("mas");
        c.salvar();
        
        List<Cliente> list = c.getAll();
        System.out.println(list.size());
    }
    
    public static void testeForeach(){
        List<String> ls = new ArrayList<>();
        List<Cliente> listTemp = new ArrayList<>();
        Cliente c = new Cliente();
        listTemp = c.getAll();
        
        for (Cliente la : listTemp) {
            ls.add(la.getNome());
        }
        
        System.out.println(ls.size());
    }
}
