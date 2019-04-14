package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
       
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(evt -> handlePush(evt));
        boutons.add(add);   add.addActionListener(evt -> handleAdd(evt));
        boutons.add(sub);   sub.addActionListener(evt -> handleSub(evt));
        boutons.add(mul);   mul.addActionListener(evt -> handleMul(evt));
        boutons.add(div);   div.addActionListener(evt -> handleDiv(evt));
        boutons.add(clear); clear.addActionListener(evt -> handleClear(evt));
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
           boolean hasTwoOrMoreOp = pile.taille() >= 2;
        this.add.setEnabled(hasTwoOrMoreOp);
        this.sub.setEnabled(hasTwoOrMoreOp);
        this.mul.setEnabled(hasTwoOrMoreOp);
        this.div.setEnabled(hasTwoOrMoreOp);

        this.clear.setEnabled(!pile.estVide());        
        this.push.setEnabled(!pile.estPleine());
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }
 private void handlePush(ActionEvent evt) {
        try {
            Integer value = operande();
            pile.empiler(value);
            actualiserInterface();
        } catch (Exception ex) { }
    }

    private void handleAdd(ActionEvent evt) {
        try {
            Integer op1 = pile.depiler(), op2 = pile.depiler();
            Integer result = op1 + op2;
            pile.empiler(result);
            actualiserInterface();
        } catch (Exception ex) { }
    }

    private void handleSub(ActionEvent evt) {
        try {
            Integer op1 = pile.depiler(), op2 = pile.depiler();
            Integer result = op2 - op1;
            pile.empiler(result);
            actualiserInterface();
        } catch (Exception ex) { }
    }

    private void handleMul(ActionEvent evt) {
        try {
            Integer op1 = pile.depiler(), op2 = pile.depiler();
            Integer result = op1 * op2;
            pile.empiler(result);
            actualiserInterface();
        } catch (Exception ex) { }
    }

    private void handleDiv(ActionEvent evt) {
        try {
            if (pile.sommet() != 0) {
                Integer op1 = pile.depiler(), op2 = pile.depiler();
                Integer result = op2 / op1;
                pile.empiler(result);
                actualiserInterface();
            }
        } catch (Exception ex) { }
    }

    private void handleClear(ActionEvent evt) {
        while(!pile.estVide()) {
            try {
                Integer item = pile.depiler();
            } catch(PileVideException ex) {
                break;
            }
        }
        actualiserInterface();
    }

}
