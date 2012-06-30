package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import dao.DaoItinerario;
import dao.DaoRota;
import dao.DaoCidade;
import entidades.*;

public class PanelRotaItinerario extends JPanel {

    public JPanel inserirPnlRotaItinerario() {
        fontePadrao = new Font("Segoe UI", 1, 14);
        layoutRight = new FlowLayout(FlowLayout.RIGHT);
        layoutRight.setHgap(5);
        rota = new Rota();
        itinerario = new Itinerario();
        daoRota = new DaoRota();
        daoItinerario = new DaoItinerario();
        daoCidade = new DaoCidade();

        pnlRotaItinerario = new JPanel(layoutRight);
        pnlRotaItinerario.setVisible(false);
        pnlRotaItinerario.setBorder(BorderFactory.createTitledBorder(null, " ROTA / ITINERARIO ", TitledBorder.CENTER, TitledBorder.TOP, fontePadrao));
        pnlRotaItinerario.setBackground(new Color(240, 240, 240));

        inserePnlRota();
        inserePnlItinerario();

        carregaCombosCidade();
        carregaCombosRota();
        carregaCombosItinerario();

        pnlRotaItinerario.add(pnlRotaCadastro);
        pnlRotaItinerario.add(pnlItinerarioCadastro);
        pnlRotaItinerario.add(pnlRotaAlteracao);
        pnlRotaItinerario.add(pnlItinerarioAlteracao);
        pnlRotaItinerario.add(pnlRotaRemocao);
        pnlRotaItinerario.add(pnlItinerarioRemocao);

        carregaCombosCidade();
        carregaCombosRota();

        return pnlRotaItinerario;
    }

    public void inserePnlRota() {
        pnlRotaCadastro = new JPanel(layoutRight);
        pnlRotaAlteracao = new JPanel(layoutRight);
        pnlRotaRemocao = new JPanel(layoutRight);

        pnlRotaCadastro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Cadastro Rota ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, fontePadrao));
        pnlRotaAlteracao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Alteracao Rota ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, fontePadrao));
        pnlRotaRemocao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Remocao Rota ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, fontePadrao));

        pnlRotaCadastro.setBackground(new Color(240, 240, 240));
        pnlRotaAlteracao.setBackground(new Color(240, 240, 240));
        pnlRotaRemocao.setBackground(new Color(240, 240, 240));

        pnlRotaCadastro.setPreferredSize(new Dimension(320, 180));
        pnlRotaAlteracao.setPreferredSize(new Dimension(320, 210));
        pnlRotaRemocao.setPreferredSize(new Dimension(320, 140));

        //cadastro
        lblRotaCadastroOrigem = new JLabel("Origem:");
        lblRotaCadastroDestino = new JLabel("Destino:");
        lblRotaCadastroDuracao = new JLabel("Duracao:");
        btnRotaCadastrar = new JButton("Cadastrar");
        btnRotaCadastroLimpa = new JButton("Limpar Campos");
        cboRotaCadastroOrigem = new JComboBox(new String[]{"Selecione"});
        cboRotaCadastroDestino = new JComboBox(new String[]{"Selecione"});
        txtRotaCadastroDuracao = new JTextField("", 19);
        cboRotaCadastroOrigemOculto = new JComboBox(new String[]{"Selecione"});
        cboRotaCadastroDestinoOculto = new JComboBox(new String[]{"Selecione"});

        lblRotaCadastroOrigem.setPreferredSize(new Dimension(70, 30));
        lblRotaCadastroDestino.setPreferredSize(new Dimension(70, 30));
        lblRotaCadastroDuracao.setPreferredSize(new Dimension(70, 30));
        btnRotaCadastrar.setPreferredSize(new Dimension(100, 32));
        btnRotaCadastroLimpa.setPreferredSize(new Dimension(125, 32));
        cboRotaCadastroOrigem.setPreferredSize(new Dimension(222, 30));
        cboRotaCadastroDestino.setPreferredSize(new Dimension(222, 30));

        pnlRotaCadastro.add(lblRotaCadastroOrigem);
        pnlRotaCadastro.add(cboRotaCadastroOrigem);
        pnlRotaCadastro.add(lblRotaCadastroDestino);
        pnlRotaCadastro.add(cboRotaCadastroDestino);
        pnlRotaCadastro.add(lblRotaCadastroDuracao);
        pnlRotaCadastro.add(txtRotaCadastroDuracao);
        pnlRotaCadastro.add(btnRotaCadastroLimpa);
        pnlRotaCadastro.add(btnRotaCadastrar);

        //alteracao
        lblRotaAlteracaoDuracao = new JLabel("Duracao:");
        lblRotaAlteracaoOrigem = new JLabel("Origem:");
        lblRotaAlteracaoRota = new JLabel("Rota:");
        lblRotaAlteracaoDestino = new JLabel("Destino:");
        btnRotaAlterar = new JButton("Alterar");
        cboRotaAlteracaoIdRota = new JComboBox(new String[]{"Selecione"});
        cboRotaAlteracaoIdRotaOculto = new JComboBox(new String[]{"Selecione"});
        cboRotaAlteracaoOrigem = new JComboBox(new String[]{"Selecione"});
        cboRotaAlteracaoDestino = new JComboBox(new String[]{"Selecione"});
        cboRotaAlteracaoOrigemOculto = new JComboBox(new String[]{"Selecione"});
        cboRotaAlteracaoDestinoOculto = new JComboBox(new String[]{"Selecione"});
        txtRotaAlteracaoDuracao = new JTextField("", 18);

        lblRotaAlteracaoDuracao.setPreferredSize(new Dimension(70, 30));
        btnRotaAlterar.setPreferredSize(new Dimension(100, 32));
        cboRotaAlteracaoIdRota.setPreferredSize(new Dimension(222, 30));
        cboRotaAlteracaoOrigem.setPreferredSize(new Dimension(222, 30));
        cboRotaAlteracaoDestino.setPreferredSize(new Dimension(222, 30));
        lblRotaAlteracaoOrigem.setPreferredSize(new Dimension(70, 30));
        lblRotaAlteracaoRota.setPreferredSize(new Dimension(70, 30));
        lblRotaAlteracaoDestino.setPreferredSize(new Dimension(70, 30));

        cboRotaAlteracaoIdRotaOculto.setVisible(false);
        pnlRotaAlteracao.add(lblRotaAlteracaoRota);
        pnlRotaAlteracao.add(cboRotaAlteracaoIdRota);
        pnlRotaAlteracao.add(lblRotaAlteracaoOrigem);
        pnlRotaAlteracao.add(cboRotaAlteracaoOrigem);
        pnlRotaAlteracao.add(lblRotaAlteracaoDestino);
        pnlRotaAlteracao.add(cboRotaAlteracaoDestino);
        pnlRotaAlteracao.add(lblRotaAlteracaoDuracao);
        pnlRotaAlteracao.add(txtRotaAlteracaoDuracao);
        pnlRotaAlteracao.add(btnRotaAlterar);

        //remocao
        lblRotaRemocaoOrigemDestino = new JLabel("Rota:");
        lblRotaRemocaoDuracao = new JLabel("Duracao:");
        cboRotaRemocaoIdRota = new JComboBox(new String[]{"Selecione"});
        cboRotaRemocaoIdRotaOculto = new JComboBox(new String[]{"Selecione"});
        lblRotaRemocaoDuracaoR = new JLabel();
        btnRotaRemover = new JButton("Remover");

        cboRotaRemocaoIdRota.setPreferredSize(new Dimension(222, 30));
        lblRotaRemocaoOrigemDestino.setPreferredSize(new Dimension(70, 30));
        lblRotaRemocaoDuracao.setPreferredSize(new Dimension(70, 30));
        lblRotaRemocaoDuracaoR.setPreferredSize(new Dimension(222, 30));
        btnRotaRemover.setPreferredSize(new Dimension(100, 32));

        cboRotaRemocaoIdRotaOculto.setVisible(false);
        pnlRotaRemocao.add(lblRotaRemocaoOrigemDestino);
        pnlRotaRemocao.add(cboRotaRemocaoIdRota);
        pnlRotaRemocao.add(lblRotaRemocaoDuracao);
        pnlRotaRemocao.add(lblRotaRemocaoDuracaoR);
        pnlRotaRemocao.add(btnRotaRemover);

        btnRotaCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnRotaCadastrarClick(evt);
            }
        });

        btnRotaCadastroLimpa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnRotaCadastroLimpaClick(evt);
            }
        });

        btnRotaAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnRotaAlterarClick(evt);
            }
        });

        btnRotaRemover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnRotaRemoverClick(evt);
            }
        });

        cboRotaAlteracaoIdRota.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboRotaAlteracaoIdRotaClick(evt);
            }
        });

        cboRotaRemocaoIdRota.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboRotaRemocaoOrigemDestinoClick(evt);
            }
        });

        cboRotaCadastroOrigem.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboRotaCadastroOrigemClick(evt);
            }
        });

        cboRotaCadastroDestino.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboRotaCadastroDestinoClick(evt);
            }
        });

        cboRotaAlteracaoOrigem.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboRotaAlteracaoCidadeOrigemClick(evt);
            }
        });

        cboRotaAlteracaoDestino.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboRotaAlteracaoCidadeDestinoClick(evt);
            }
        });

    }

    public void inserePnlItinerario() {
        pnlItinerarioCadastro = new JPanel(layoutRight);
        pnlItinerarioAlteracao = new JPanel(layoutRight);
        pnlItinerarioRemocao = new JPanel(layoutRight);

        pnlItinerarioCadastro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Cadastro Itinerario ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, fontePadrao));
        pnlItinerarioAlteracao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Alteracao Itinerario ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, fontePadrao));
        pnlItinerarioRemocao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)), " Remocao Itinerario ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, fontePadrao));

        pnlItinerarioCadastro.setBackground(new Color(240, 240, 240));
        pnlItinerarioAlteracao.setBackground(new Color(240, 240, 240));
        pnlItinerarioRemocao.setBackground(new Color(240, 240, 240));

        pnlItinerarioCadastro.setPreferredSize(new Dimension(320, 180));
        pnlItinerarioAlteracao.setPreferredSize(new Dimension(320, 210));
        pnlItinerarioRemocao.setPreferredSize(new Dimension(320, 140));

        //cadastro
        JLabel lblItinerarioCadatroAux = new JLabel("");
        lblItinerarioCadastroOrigem = new JLabel("Origem:");
        lblItinerarioCadastroDestino = new JLabel("Destino:");
        btnItinerarioCadastrar = new JButton("Cadastrar");
        btnItinerarioCadastroLimpa = new JButton("Limpar Campos");
        cboItinerarioCadastroOrigem = new JComboBox(new String[]{"Selecione"});
        cboItinerarioCadastroOrigemOculto = new JComboBox(new String[]{"Selecione"});
        cboItinerarioCadastroDestino = new JComboBox(new String[]{"Selecione"});
        cboItinerarioCadastroDestinoOculto = new JComboBox(new String[]{"Selecione"});

        lblItinerarioCadastroOrigem.setPreferredSize(new Dimension(70, 30));
        lblItinerarioCadastroDestino.setPreferredSize(new Dimension(70, 30));
        btnItinerarioCadastrar.setPreferredSize(new Dimension(100, 32));
        btnItinerarioCadastroLimpa.setPreferredSize(new Dimension(125, 32));
        cboItinerarioCadastroOrigem.setPreferredSize(new Dimension(222, 30));
        cboItinerarioCadastroDestino.setPreferredSize(new Dimension(222, 30));
        lblItinerarioCadatroAux.setPreferredSize(new Dimension(292, 30));


        pnlItinerarioCadastro.add(lblItinerarioCadastroOrigem);
        pnlItinerarioCadastro.add(cboItinerarioCadastroOrigem);
        pnlItinerarioCadastro.add(lblItinerarioCadastroDestino);
        pnlItinerarioCadastro.add(cboItinerarioCadastroDestino);
        pnlItinerarioCadastro.add(lblItinerarioCadatroAux);
        pnlItinerarioCadastro.add(btnItinerarioCadastroLimpa);
        pnlItinerarioCadastro.add(btnItinerarioCadastrar);

        //alteracao
        JLabel lblItinerarioAlteracaoAux = new JLabel("");
        lblItinerarioAlteracaoOrigem = new JLabel("Origem:");
        lblItinerarioAlteracaoOrigemDestino = new JLabel("Itinerario:");
        lblItinerarioAlteracaoDestino = new JLabel("Destino:");
        btnItinerarioAlterar = new JButton("Alterar");
        cboItinerarioAlteracaoIdItinerario = new JComboBox(new String[]{"Selecione"});
        cboItinerarioAlteracaoIdItinerarioOculto = new JComboBox(new String[]{"Selecione"});
        cboItinerarioAlteracaoOrigem = new JComboBox(new String[]{"Selecione"});
        cboItinerarioAlteracaoOrigemOculto = new JComboBox(new String[]{"Selecione"});
        cboItinerarioAlteracaoDestino = new JComboBox(new String[]{"Selecione"});
        cboItinerarioAlteracaoDestinoOculto = new JComboBox(new String[]{"Selecione"});

        btnItinerarioAlterar.setPreferredSize(new Dimension(100, 32));
        cboItinerarioAlteracaoIdItinerario.setPreferredSize(new Dimension(222, 30));
        cboItinerarioAlteracaoOrigem.setPreferredSize(new Dimension(222, 30));
        cboItinerarioAlteracaoDestino.setPreferredSize(new Dimension(222, 30));
        lblItinerarioAlteracaoAux.setPreferredSize(new Dimension(292, 30));
        lblItinerarioAlteracaoOrigem.setPreferredSize(new Dimension(70, 30));
        lblItinerarioAlteracaoOrigemDestino.setPreferredSize(new Dimension(70, 30));
        lblItinerarioAlteracaoDestino.setPreferredSize(new Dimension(70, 30));

        cboItinerarioAlteracaoIdItinerarioOculto.setVisible(false);
        pnlItinerarioAlteracao.add(lblItinerarioAlteracaoOrigemDestino);
        pnlItinerarioAlteracao.add(cboItinerarioAlteracaoIdItinerario);
        pnlItinerarioAlteracao.add(lblItinerarioAlteracaoOrigem);
        pnlItinerarioAlteracao.add(cboItinerarioAlteracaoOrigem);
        pnlItinerarioAlteracao.add(lblItinerarioAlteracaoDestino);
        pnlItinerarioAlteracao.add(cboItinerarioAlteracaoDestino);
        pnlItinerarioAlteracao.add(lblItinerarioAlteracaoAux);
        pnlItinerarioAlteracao.add(btnItinerarioAlterar);

        //remocao
        lblItinerarioRemocaoOrigemDestino = new JLabel("Itinerario:");
        JLabel lblItinerarioRemocaoAux = new JLabel("");
        cboItinerarioRemocaoIdItinerario = new JComboBox(new String[]{"Selecione"});
        cboItinerarioRemocaoIdItinerarioOculto = new JComboBox(new String[]{"Selecione"});
        btnItinerarioRemover = new JButton("Remover");

        cboItinerarioRemocaoIdItinerario.setPreferredSize(new Dimension(222, 30));
        lblItinerarioRemocaoOrigemDestino.setPreferredSize(new Dimension(70, 30));
        lblItinerarioRemocaoAux.setPreferredSize(new Dimension(292, 30));

        btnItinerarioRemover.setPreferredSize(new Dimension(100, 32));

        cboItinerarioRemocaoIdItinerarioOculto.setVisible(false);
        pnlItinerarioRemocao.add(lblItinerarioRemocaoOrigemDestino);
        pnlItinerarioRemocao.add(cboItinerarioRemocaoIdItinerario);
        pnlItinerarioRemocao.add(lblItinerarioRemocaoAux);
        pnlItinerarioRemocao.add(btnItinerarioRemover);

        /* Cadastro */
        btnItinerarioCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnItinerarioCadastrarClick(evt);
            }
        });

        btnItinerarioCadastroLimpa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnItinerarioCadastroLimpaClick(evt);
            }
        });

        cboItinerarioCadastroOrigem.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboItinerarioCadastroOrigemClick(evt);
            }
        });

        cboItinerarioCadastroDestino.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboItinerarioCadastroDestinoClick(evt);
            }
        });

        /* Altera��o */
        btnItinerarioAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnItinerarioAlterarClick(evt);
            }
        });

        cboItinerarioAlteracaoIdItinerario.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboItinerarioAlteracaoIdItinerarioClick(evt);
            }
        });

        cboItinerarioAlteracaoOrigem.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboItinerarioAlteracaoOrigemClick(evt);
            }
        });

        cboItinerarioAlteracaoDestino.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboItinerarioAlteracaoDestinoClick(evt);
            }
        });

        /* Remover */
        btnItinerarioRemover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                btnItinerarioRemoverClick(evt);
            }
        });

        cboItinerarioRemocaoIdItinerario.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                cboItinerarioRemocaoIdItinerarioClick(evt);
            }
        });
    }

    public void focusTxtPrincipal() {
        cboRotaCadastroOrigem.requestFocus();
    }

    public void carregaCombosCidade() {
        ArrayList<Cidade> arraylist = daoCidade.consultarTodasCidades();
        /* Rota */

        //cadastro
        cboRotaCadastroOrigem.removeAllItems();
        cboRotaCadastroOrigem.addItem("Selecione");
        cboRotaCadastroDestino.removeAllItems();
        cboRotaCadastroDestino.addItem("Selecione");
        cboRotaCadastroOrigemOculto.removeAllItems();
        cboRotaCadastroOrigemOculto.addItem("Selecione");
        cboRotaCadastroDestinoOculto.removeAllItems();
        cboRotaCadastroDestinoOculto.addItem("Selecione");

        //alteracao
        cboRotaAlteracaoOrigem.removeAllItems();
        cboRotaAlteracaoOrigem.addItem("Selecione");
        cboRotaAlteracaoDestino.removeAllItems();
        cboRotaAlteracaoDestino.addItem("Selecione");
        cboRotaAlteracaoOrigemOculto.removeAllItems();
        cboRotaAlteracaoOrigemOculto.addItem("Selecione");
        cboRotaAlteracaoDestinoOculto.removeAllItems();
        cboRotaAlteracaoDestinoOculto.addItem("Selecione");

        /* Itinerario */

        //cadastro
        cboItinerarioCadastroOrigem.removeAllItems();
        cboItinerarioCadastroOrigem.addItem("Selecione");
        cboItinerarioCadastroDestino.removeAllItems();
        cboItinerarioCadastroDestino.addItem("Selecione");
        cboItinerarioCadastroOrigemOculto.removeAllItems();
        cboItinerarioCadastroOrigemOculto.addItem("Selecione");
        cboItinerarioCadastroDestinoOculto.removeAllItems();
        cboItinerarioCadastroDestinoOculto.addItem("Selecione");

        //alteracao
        cboItinerarioAlteracaoOrigem.removeAllItems();
        cboItinerarioAlteracaoOrigem.addItem("Selecione");
        cboItinerarioAlteracaoDestino.removeAllItems();
        cboItinerarioAlteracaoDestino.addItem("Selecione");
        cboItinerarioAlteracaoOrigemOculto.removeAllItems();
        cboItinerarioAlteracaoOrigemOculto.addItem("Selecione");
        cboItinerarioAlteracaoDestinoOculto.removeAllItems();
        cboItinerarioAlteracaoDestinoOculto.addItem("Selecione");

        for (int i = 0; i < arraylist.size(); i++) {
            /* Rota */
            cboRotaCadastroOrigem.addItem(arraylist.get(i).getNome());
            cboRotaCadastroOrigemOculto.addItem(arraylist.get(i).getId());
            cboRotaCadastroDestino.addItem(arraylist.get(i).getNome());
            cboRotaCadastroDestinoOculto.addItem(arraylist.get(i).getId());
            cboRotaAlteracaoOrigem.addItem(arraylist.get(i).getNome());
            cboRotaAlteracaoDestino.addItem(arraylist.get(i).getNome());
            cboRotaAlteracaoOrigemOculto.addItem(arraylist.get(i).getId());
            cboRotaAlteracaoDestinoOculto.addItem(arraylist.get(i).getId());

            /* Itinerario */
            cboItinerarioCadastroOrigem.addItem(arraylist.get(i).getNome());
            cboItinerarioCadastroOrigemOculto.addItem(arraylist.get(i).getId());
            cboItinerarioCadastroDestino.addItem(arraylist.get(i).getNome());
            cboItinerarioCadastroDestinoOculto.addItem(arraylist.get(i).getId());
            cboItinerarioAlteracaoOrigem.addItem(arraylist.get(i).getNome());
            cboItinerarioAlteracaoDestino.addItem(arraylist.get(i).getNome());
            cboItinerarioAlteracaoOrigemOculto.addItem(arraylist.get(i).getId());
            cboItinerarioAlteracaoDestinoOculto.addItem(arraylist.get(i).getId());
        }

    }

    public void carregaCombosRota() {
        ArrayList<Rota> arraylistRotas = daoRota.consultarTodasRotas();
        //alteracao
        cboRotaAlteracaoIdRota.removeAllItems();
        cboRotaAlteracaoIdRota.addItem("Selecione");
        cboRotaAlteracaoIdRotaOculto.removeAllItems();
        cboRotaAlteracaoIdRotaOculto.addItem("Selecione");
        //remocao
        cboRotaRemocaoIdRota.removeAllItems();
        cboRotaRemocaoIdRota.addItem("Selecione");
        cboRotaRemocaoIdRotaOculto.removeAllItems();
        cboRotaRemocaoIdRotaOculto.addItem("Selecione");
        for (int i = 0; i < arraylistRotas.size(); i++) {
            cboRotaAlteracaoIdRota.addItem(arraylistRotas.get(i).getRota_cidadeOrigem() + " - " + arraylistRotas.get(i).getRota_cidadeDestino());
            cboRotaRemocaoIdRota.addItem(arraylistRotas.get(i).getRota_cidadeOrigem() + " - " + arraylistRotas.get(i).getRota_cidadeDestino());
            cboRotaAlteracaoIdRotaOculto.addItem(arraylistRotas.get(i).getId());
            cboRotaRemocaoIdRotaOculto.addItem(arraylistRotas.get(i).getId());
        }

    }

    public void carregaCombosItinerario() {
        ArrayList<Itinerario> arraylistItinerarios = daoItinerario.consultarTodosItinerarios2();
        //alteracao
        cboItinerarioAlteracaoIdItinerario.removeAllItems();
        cboItinerarioAlteracaoIdItinerario.addItem("Selecione");
        cboItinerarioAlteracaoIdItinerarioOculto.removeAllItems();
        cboItinerarioAlteracaoIdItinerarioOculto.addItem("Selecione");
        //remocao
        cboItinerarioRemocaoIdItinerario.removeAllItems();
        cboItinerarioRemocaoIdItinerario.addItem("Selecione");
        cboItinerarioRemocaoIdItinerarioOculto.removeAllItems();
        cboItinerarioRemocaoIdItinerarioOculto.addItem("Selecione");
        for (int i = 0; i < arraylistItinerarios.size(); i++) {
            cboItinerarioAlteracaoIdItinerario.addItem(arraylistItinerarios.get(i).getItinerario_cidadeOrigem() + " - " + arraylistItinerarios.get(i).getItinerario_cidadeDestino());
            cboItinerarioRemocaoIdItinerario.addItem(arraylistItinerarios.get(i).getItinerario_cidadeOrigem() + " - " + arraylistItinerarios.get(i).getItinerario_cidadeDestino());
            cboItinerarioAlteracaoIdItinerarioOculto.addItem(arraylistItinerarios.get(i).getId());
            cboItinerarioRemocaoIdItinerarioOculto.addItem(arraylistItinerarios.get(i).getId());
        }

    }

    /* Rota */
    /* Cadastro */
    private void btnRotaCadastrarClick(ActionEvent evt) {
        if (cboRotaCadastroOrigem.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione uma Origem.");
            cboRotaCadastroOrigem.requestFocus();
        } else if (cboRotaCadastroDestino.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione um Destino.");
            cboRotaCadastroDestino.requestFocus();
        } else if (txtRotaCadastroDuracao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite uma Duracao.");
            txtRotaCadastroDuracao.requestFocus();
        } else {
            rota.setRotaDuracao(txtRotaCadastroDuracao.getText());
            rota.setRota_cidadeOrigemId(Integer.parseInt(String.valueOf(cboRotaCadastroOrigemOculto.getSelectedItem())));
            rota.setRota_cidadeDestinoId(Integer.parseInt(String.valueOf(cboRotaCadastroDestinoOculto.getSelectedItem())));
            int verifica = daoRota.cadastrarRota(rota);
            if (verifica == 0) {
                JOptionPane.showMessageDialog(null, "Rota cadastrada com sucesso!");
                txtRotaCadastroDuracao.setText("");
                cboRotaCadastroOrigem.setSelectedItem("Selecione");
                cboRotaCadastroDestino.setSelectedItem("Selecione");
                carregaCombosCidade();
                carregaCombosRota();
                cboRotaCadastroOrigem.requestFocus();
            } else if (verifica == 1) {
                JOptionPane.showMessageDialog(null, "Rota ja existente.");
                cboRotaCadastroOrigem.setSelectedItem("Selecione");
                cboRotaCadastroOrigem.requestFocus();
                cboRotaCadastroDestino.setSelectedItem("Selecione");
            } else if (verifica == 2) {
                JOptionPane.showMessageDialog(null, "Selecione um Destino diferente da Origem.");
                cboRotaCadastroDestino.setSelectedItem("Selecione");
                cboRotaCadastroDestino.requestFocus();
            }
        }
    }

    private void btnRotaCadastroLimpaClick(ActionEvent evt) {
        txtRotaCadastroDuracao.setText("");
        cboRotaCadastroOrigem.setSelectedItem("Selecione");
        cboRotaCadastroOrigemOculto.setSelectedItem("Selecione");
        cboRotaCadastroDestino.setSelectedItem("Selecione");
        cboRotaCadastroDestinoOculto.setSelectedItem("Selecione");
        cboRotaCadastroOrigem.requestFocus();
    }

    private void cboRotaCadastroOrigemClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboRotaCadastroOrigem.getSelectedItem().equals("Selecione"))) {
                cboRotaCadastroOrigemOculto.setSelectedIndex(cboRotaCadastroOrigem.getSelectedIndex());
            } else {
                txtRotaCadastroDuracao.setText("");
                cboRotaCadastroOrigem.setSelectedItem("Selecione");
                cboRotaCadastroDestino.setSelectedItem("Selecione");
            }
        }
    }

    private void cboRotaCadastroDestinoClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboRotaCadastroDestino.getSelectedItem().equals("Selecione"))) {
                cboRotaCadastroDestinoOculto.setSelectedIndex(cboRotaCadastroDestino.getSelectedIndex());
            } else {
                txtRotaCadastroDuracao.setText("");
                cboRotaCadastroOrigem.setSelectedItem("Selecione");
                cboRotaCadastroDestino.setSelectedItem("Selecione");
            }
        }
    }

    /* Alteracao */
    private void btnRotaAlterarClick(ActionEvent evt) {
        if (cboRotaAlteracaoIdRota.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione uma Rota.");
            cboRotaAlteracaoIdRota.requestFocus();
        } else if (cboRotaAlteracaoOrigem.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione uma Origem.");
            cboRotaAlteracaoOrigem.requestFocus();
        } else if (cboRotaAlteracaoDestino.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione um Destino.");
            cboRotaAlteracaoDestino.requestFocus();
        } else if (txtRotaAlteracaoDuracao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite a Duracao da Rota.");
            txtRotaAlteracaoDuracao.requestFocus();
        } else {
            rota.setId(Integer.parseInt(String.valueOf(cboRotaAlteracaoIdRotaOculto.getSelectedItem())));
            rota.setRotaDuracao(txtRotaAlteracaoDuracao.getText());
            rota.setRota_cidadeOrigemId(Integer.parseInt(String.valueOf(cboRotaAlteracaoOrigemOculto.getSelectedItem())));
            rota.setRota_cidadeDestinoId(Integer.parseInt(String.valueOf(cboRotaAlteracaoDestinoOculto.getSelectedItem())));
            int verifica = daoRota.alterarRota(rota);
            if (verifica == 0) {
                JOptionPane.showMessageDialog(null, "Rota alterada com sucesso!");
            } else if (verifica == 1) {
                JOptionPane.showMessageDialog(null, "Rota ja existente!");
            } else if (verifica == 2) {
                JOptionPane.showMessageDialog(null, "Nao foi possivel alterar essa Rota pois ela pertence a um itinerario!");
            } else if (verifica == 3) {
                JOptionPane.showMessageDialog(null, "Selecione um Destino diferente da Origem!");
            }
            txtRotaAlteracaoDuracao.setText("");
            carregaCombosCidade();
            carregaCombosRota();
            cboRotaAlteracaoIdRota.setSelectedItem("Selecione");
            cboRotaAlteracaoIdRotaOculto.setSelectedItem("Selecione");
            cboRotaAlteracaoOrigem.setSelectedItem("Selecione");
            cboRotaAlteracaoOrigemOculto.setSelectedItem("Selecione");
            cboRotaAlteracaoDestino.setSelectedItem("Selecione");
            cboRotaAlteracaoDestinoOculto.setSelectedItem("Selecione");
            cboRotaAlteracaoIdRota.requestFocus();
        }
    }

    private void cboRotaAlteracaoIdRotaClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboRotaAlteracaoIdRota.getSelectedItem().equals("Selecione"))) {
                cboRotaAlteracaoIdRotaOculto.setSelectedIndex(cboRotaAlteracaoIdRota.getSelectedIndex());
                rota.setId(Integer.parseInt(String.valueOf(cboRotaAlteracaoIdRotaOculto.getSelectedItem())));
                Rota aux = daoRota.consultaRota(rota);
                txtRotaAlteracaoDuracao.setText(aux.getRotaDuracao());
                cboRotaAlteracaoOrigemOculto.setSelectedItem(aux.getRota_cidadeOrigemId());
                cboRotaAlteracaoDestinoOculto.setSelectedItem(aux.getRota_cidadeDestinoId());
                cboRotaAlteracaoOrigem.setSelectedIndex(cboRotaAlteracaoOrigemOculto.getSelectedIndex());
                cboRotaAlteracaoDestino.setSelectedIndex(cboRotaAlteracaoDestinoOculto.getSelectedIndex());
            } else {
                txtRotaAlteracaoDuracao.setText("");
                cboRotaAlteracaoOrigem.setSelectedItem("Selecione");
                cboRotaAlteracaoDestino.setSelectedItem("Selecione");
                cboRotaAlteracaoOrigemOculto.setSelectedItem("Selecione");
                cboRotaAlteracaoDestinoOculto.setSelectedItem("Selecione");
            }
        }
    }

    private void cboRotaAlteracaoCidadeOrigemClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboRotaAlteracaoOrigem.getSelectedItem().equals("Selecione"))) {
                cboRotaAlteracaoOrigemOculto.setSelectedIndex(cboRotaAlteracaoOrigem.getSelectedIndex());
            }
        }
    }

    private void cboRotaAlteracaoCidadeDestinoClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboRotaAlteracaoDestino.getSelectedItem().equals("Selecione"))) {
                cboRotaAlteracaoDestinoOculto.setSelectedIndex(cboRotaAlteracaoDestino.getSelectedIndex());
            }
        }
    }

    /* Remocao */
    private void btnRotaRemoverClick(ActionEvent evt) {
        int confirma = 0;
        if (cboRotaRemocaoIdRota.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione uma Rota para remover.");
            cboRotaRemocaoIdRota.requestFocus();
        } else {
            confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o registro?");
            if (confirma == JOptionPane.YES_OPTION) {
                rota.setId(Integer.parseInt(String.valueOf(cboRotaRemocaoIdRotaOculto.getSelectedItem())));
                boolean verifica = daoRota.removerRota(rota);
                if (verifica) {
                    JOptionPane.showMessageDialog(null, "Rota removida com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel remover Rota. Ela esta sendo usada por algum Itinerario.");
                }
                cboRotaRemocaoIdRota.setSelectedItem("Selecione");
                cboRotaRemocaoIdRotaOculto.setSelectedItem("Selecione");
                lblRotaRemocaoDuracaoR.setText("");
                cboRotaRemocaoIdRota.requestFocus();
            } else {
                cboRotaRemocaoIdRota.setSelectedItem("Selecione");
                cboRotaRemocaoIdRotaOculto.setSelectedItem("Selecione");
                lblRotaRemocaoDuracaoR.setText("");
                cboRotaRemocaoIdRota.requestFocus();
            }
            carregaCombosCidade();
            carregaCombosRota();
        }
    }

    private void cboRotaRemocaoOrigemDestinoClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboRotaRemocaoIdRota.getSelectedItem().equals("Selecione"))) {
                cboRotaRemocaoIdRotaOculto.setSelectedIndex(cboRotaRemocaoIdRota.getSelectedIndex());
                rota.setId(Integer.parseInt(String.valueOf(cboRotaRemocaoIdRotaOculto.getSelectedItem())));
                rota = daoRota.consultaRota(rota);
                lblRotaRemocaoDuracaoR.setText(rota.getRotaDuracao());
            } else {
                cboRotaRemocaoIdRotaOculto.setSelectedIndex(cboRotaRemocaoIdRota.getSelectedIndex());
                lblRotaRemocaoDuracaoR.setText("");
            }
        }
    }

    /* Itinerario */
    /* Cadastro */
    private void btnItinerarioCadastrarClick(ActionEvent evt) {
        if (cboItinerarioCadastroOrigem.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione uma Origem.");
            cboItinerarioCadastroOrigem.requestFocus();
        } else if (cboItinerarioCadastroDestino.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione um Destino.");
            cboItinerarioCadastroDestino.requestFocus();
        } else {
            itinerario.setItinerario_cidadeOrigemId(Integer.parseInt(String.valueOf(cboItinerarioCadastroOrigemOculto.getSelectedItem())));
            itinerario.setItinerario_cidadeDestinoId(Integer.parseInt(String.valueOf(cboItinerarioCadastroDestinoOculto.getSelectedItem())));
            int verifica = daoItinerario.cadastrarItinerario(itinerario);
            if (verifica == 0) {
                JOptionPane.showMessageDialog(null, "Itinerario cadastrado com sucesso!");
                cboItinerarioCadastroOrigem.setSelectedItem("Selecione");
                cboItinerarioCadastroDestino.setSelectedItem("Selecione");
                carregaCombosCidade();
                carregaCombosItinerario();
                cboItinerarioCadastroOrigem.requestFocus();
            } else if (verifica == 1) {
                JOptionPane.showMessageDialog(null, "Itinerario ja existente.");
                cboItinerarioCadastroOrigem.setSelectedItem("Selecione");
                cboItinerarioCadastroDestino.setSelectedItem("Selecione");
                cboItinerarioCadastroOrigem.requestFocus();
            } else if (verifica == 2) {
                JOptionPane.showMessageDialog(null, "Selecione um Destino diferente da Origem.");
                cboItinerarioCadastroOrigem.setSelectedItem("Selecione");
                cboItinerarioCadastroDestino.setSelectedItem("Selecione");
                cboItinerarioCadastroOrigem.requestFocus();
            }
        }
    }

    private void btnItinerarioCadastroLimpaClick(ActionEvent evt) {
        cboItinerarioCadastroOrigem.setSelectedItem("Selecione");
        cboItinerarioCadastroOrigemOculto.setSelectedItem("Selecione");
        cboItinerarioCadastroDestino.setSelectedItem("Selecione");
        cboItinerarioCadastroDestinoOculto.setSelectedItem("Selecione");
        cboItinerarioCadastroOrigem.requestFocus();
    }

    private void cboItinerarioCadastroOrigemClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboItinerarioCadastroOrigem.getSelectedItem().equals("Selecione"))) {
                cboItinerarioCadastroOrigemOculto.setSelectedIndex(cboItinerarioCadastroOrigem.getSelectedIndex());
            } else {
                cboItinerarioCadastroOrigem.setSelectedItem("Selecione");
                cboItinerarioCadastroDestino.setSelectedItem("Selecione");
            }
        }
    }

    private void cboItinerarioCadastroDestinoClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboItinerarioCadastroDestino.getSelectedItem().equals("Selecione"))) {
                cboItinerarioCadastroDestinoOculto.setSelectedIndex(cboItinerarioCadastroDestino.getSelectedIndex());
            } else {
                cboItinerarioCadastroOrigem.setSelectedItem("Selecione");
                cboItinerarioCadastroDestino.setSelectedItem("Selecione");
            }
        }
    }

    /* Alteracao */
    private void btnItinerarioAlterarClick(ActionEvent evt) {
        if (cboItinerarioAlteracaoIdItinerario.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione um Itiner�rio.");
            cboItinerarioAlteracaoIdItinerario.requestFocus();
        } else if (cboItinerarioAlteracaoOrigem.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione uma Origem.");
            cboItinerarioAlteracaoOrigem.requestFocus();
        } else if (cboItinerarioAlteracaoDestino.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione um Destino.");
            cboItinerarioAlteracaoDestino.requestFocus();
        } else {
            itinerario.setId(Integer.parseInt(String.valueOf(cboItinerarioAlteracaoIdItinerarioOculto.getSelectedItem())));
            itinerario.setItinerario_cidadeOrigemId(Integer.parseInt(String.valueOf(cboItinerarioAlteracaoOrigemOculto.getSelectedItem())));
            itinerario.setItinerario_cidadeDestinoId(Integer.parseInt(String.valueOf(cboItinerarioAlteracaoDestinoOculto.getSelectedItem())));
            int verifica = daoItinerario.alterarItinerario(itinerario);
            if (verifica == 0) {
                JOptionPane.showMessageDialog(null, "Itinerario alterado com sucesso!");
            } else if (verifica == 1) {
                JOptionPane.showMessageDialog(null, "Itinerario ja existente!");
            } else if (verifica == 2) {
                JOptionPane.showMessageDialog(null, "Nao foi possivel alterar esse Itinerario pois ela pertence a uma viagem!");
            } else if (verifica == 3) {
                JOptionPane.showMessageDialog(null, "Selecione um  Destino diferente da Origem!");
            }
            carregaCombosCidade();
            carregaCombosItinerario();
            cboItinerarioAlteracaoIdItinerario.setSelectedItem("Selecione");
            cboItinerarioAlteracaoIdItinerarioOculto.setSelectedItem("Selecione");
            cboItinerarioAlteracaoOrigem.setSelectedItem("Selecione");
            cboItinerarioAlteracaoOrigemOculto.setSelectedItem("Selecione");
            cboItinerarioAlteracaoDestino.setSelectedItem("Selecione");
            cboItinerarioAlteracaoDestinoOculto.setSelectedItem("Selecione");
            cboItinerarioAlteracaoIdItinerario.requestFocus();
        }
    }

    private void cboItinerarioAlteracaoIdItinerarioClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboItinerarioAlteracaoIdItinerario.getSelectedItem().equals("Selecione"))) {
                cboItinerarioAlteracaoIdItinerarioOculto.setSelectedIndex(cboItinerarioAlteracaoIdItinerario.getSelectedIndex());
                itinerario.setId(Integer.parseInt(String.valueOf(cboItinerarioAlteracaoIdItinerarioOculto.getSelectedItem())));
                Itinerario aux = daoItinerario.consultaItinerario(itinerario);
                cboItinerarioAlteracaoOrigemOculto.setSelectedItem(aux.getItinerario_cidadeOrigemId());
                cboItinerarioAlteracaoDestinoOculto.setSelectedItem(aux.getItinerario_cidadeDestinoId());
                cboItinerarioAlteracaoOrigem.setSelectedIndex(cboItinerarioAlteracaoOrigemOculto.getSelectedIndex());
                cboItinerarioAlteracaoDestino.setSelectedIndex(cboItinerarioAlteracaoDestinoOculto.getSelectedIndex());
            } else {
                cboItinerarioAlteracaoOrigem.setSelectedItem("Selecione");
                cboItinerarioAlteracaoOrigemOculto.setSelectedItem("Selecione");
                cboItinerarioAlteracaoDestino.setSelectedItem("Selecione");
                cboItinerarioAlteracaoDestinoOculto.setSelectedItem("Selecione");
            }
        }
    }

    private void cboItinerarioAlteracaoOrigemClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboItinerarioAlteracaoOrigem.getSelectedItem().equals("Selecione"))) {
                cboItinerarioAlteracaoOrigemOculto.setSelectedIndex(cboItinerarioAlteracaoOrigem.getSelectedIndex());
            }
        }
    }

    private void cboItinerarioAlteracaoDestinoClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboItinerarioAlteracaoDestino.getSelectedItem().equals("Selecione"))) {
                cboItinerarioAlteracaoDestinoOculto.setSelectedIndex(cboItinerarioAlteracaoDestino.getSelectedIndex());
            }
        }
    }

    /* Remocao */
    private void btnItinerarioRemoverClick(ActionEvent evt) {
        int confirma = 0;
        if (cboItinerarioRemocaoIdItinerario.getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Selecione um Itinerario para remover.");
            cboItinerarioRemocaoIdItinerario.requestFocus();
        } else {
            confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o registro?");
            if (confirma == JOptionPane.YES_OPTION) {
                itinerario.setId(Integer.parseInt(String.valueOf(cboItinerarioRemocaoIdItinerarioOculto.getSelectedItem())));
                boolean verifica = daoItinerario.removerItinerario(itinerario);
                if (verifica) {
                    JOptionPane.showMessageDialog(null, "Itinerario removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel remover o itinerario. Ele pertence a uma viagem.");
                }
                cboItinerarioRemocaoIdItinerario.setSelectedItem("Selecione");
                cboItinerarioRemocaoIdItinerarioOculto.setSelectedItem("Selecione");
                cboItinerarioRemocaoIdItinerario.requestFocus();
            } else {
                cboItinerarioRemocaoIdItinerario.setSelectedItem("Selecione");
                cboItinerarioRemocaoIdItinerarioOculto.setSelectedItem("Selecione");
                cboItinerarioRemocaoIdItinerario.requestFocus();
            }
            carregaCombosCidade();
            carregaCombosItinerario();
        }
    }

    private void cboItinerarioRemocaoIdItinerarioClick(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!(cboItinerarioRemocaoIdItinerario.getSelectedItem().equals("Selecione"))) {
                cboItinerarioRemocaoIdItinerarioOculto.setSelectedIndex(cboItinerarioRemocaoIdItinerario.getSelectedIndex());
                itinerario.setId(Integer.parseInt(String.valueOf(cboItinerarioRemocaoIdItinerarioOculto.getSelectedItem())));
                itinerario = daoItinerario.consultaItinerario(itinerario);
            } else {
                cboItinerarioRemocaoIdItinerarioOculto.setSelectedIndex(cboItinerarioRemocaoIdItinerario.getSelectedIndex());
            }
        }
    }
    //------- Geral
    private Font fontePadrao;
    private FlowLayout layoutRight;
    private Rota rota;
    private Itinerario itinerario;
    private DaoCidade daoCidade;
    private DaoRota daoRota;
    private DaoItinerario daoItinerario;
    private JPanel pnlRotaItinerario;
    //------- Rota
    private JPanel pnlRotaCadastro;
    private JPanel pnlRotaAlteracao;
    private JPanel pnlRotaRemocao;
    //cadastro
    private JLabel lblRotaCadastroOrigem;
    private JLabel lblRotaCadastroDestino;
    private JLabel lblRotaCadastroDuracao;
    private JButton btnRotaCadastrar;
    private JButton btnRotaCadastroLimpa;
    private JTextField txtRotaCadastroDuracao;
    private JComboBox cboRotaCadastroOrigem;
    private JComboBox cboRotaCadastroDestino;
    private JComboBox cboRotaCadastroOrigemOculto;
    private JComboBox cboRotaCadastroDestinoOculto;
    //alteracao
    private JComboBox cboRotaAlteracaoIdRota;
    private JComboBox cboRotaAlteracaoIdRotaOculto;
    private JComboBox cboRotaAlteracaoOrigem;
    private JComboBox cboRotaAlteracaoDestino;
    private JComboBox cboRotaAlteracaoOrigemOculto;
    private JComboBox cboRotaAlteracaoDestinoOculto;
    private JLabel lblRotaAlteracaoRota;
    private JLabel lblRotaAlteracaoOrigem;
    private JLabel lblRotaAlteracaoDestino;
    private JLabel lblRotaAlteracaoDuracao;
    private JButton btnRotaAlterar;
    private JTextField txtRotaAlteracaoDuracao;
    //remocao
    private JComboBox cboRotaRemocaoIdRota;
    private JComboBox cboRotaRemocaoIdRotaOculto;
    private JLabel lblRotaRemocaoOrigemDestino;
    private JLabel lblRotaRemocaoDuracao;
    private JButton btnRotaRemover;
    private JLabel lblRotaRemocaoDuracaoR;
    //------- Itinerario
    private JPanel pnlItinerarioCadastro;
    private JPanel pnlItinerarioAlteracao;
    private JPanel pnlItinerarioRemocao;
    //cadastro
    private JLabel lblItinerarioCadastroOrigem;
    private JLabel lblItinerarioCadastroDestino;
    private JButton btnItinerarioCadastrar;
    private JButton btnItinerarioCadastroLimpa;
    private JComboBox cboItinerarioCadastroOrigem;
    private JComboBox cboItinerarioCadastroOrigemOculto;
    private JComboBox cboItinerarioCadastroDestino;
    private JComboBox cboItinerarioCadastroDestinoOculto;
    //alteracao
    private JComboBox cboItinerarioAlteracaoIdItinerario;
    private JComboBox cboItinerarioAlteracaoIdItinerarioOculto;
    private JComboBox cboItinerarioAlteracaoOrigem;
    private JComboBox cboItinerarioAlteracaoOrigemOculto;
    private JComboBox cboItinerarioAlteracaoDestino;
    private JComboBox cboItinerarioAlteracaoDestinoOculto;
    private JLabel lblItinerarioAlteracaoOrigemDestino;
    private JLabel lblItinerarioAlteracaoOrigem;
    private JLabel lblItinerarioAlteracaoDestino;
    private JButton btnItinerarioAlterar;
    //remocao
    private JComboBox cboItinerarioRemocaoIdItinerario;
    private JComboBox cboItinerarioRemocaoIdItinerarioOculto;
    private JLabel lblItinerarioRemocaoOrigemDestino;
    private JButton btnItinerarioRemover;
}