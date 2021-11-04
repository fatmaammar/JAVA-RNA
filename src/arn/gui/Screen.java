package arn.gui;
import arn.Entitie.Arbre;
import arn.Entitie.Structure;
import arn.Service.ArbreService;
import arn.Service.StringsFormatting;
import arn.Service.StrucutreService;

import  javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*; // java.awt.* pour les actions des evenements
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;  // pour les structures des données : lists , hashmaps ...

public class Screen extends  JFrame{


    private JPanel panelMain;
    private JTabbedPane tabbedPane1;
    private JTextArea seqTextArea;
    private JTextArea apparTextArea;
    private JButton convToArbre;
    private JButton convToSeq;
    private JTextArea arbreTextArea;
    private JTextArea seqComTA;
    private JTextArea apparComTA;
    private JButton addStruc;
    private JList structList;
    private JTextArea affCompAT;
    private JCheckBox formeCheckBox;
    private JCheckBox sequenceCheckBox;
    private JButton btnComparer;
    private JLabel resultatLabel;
    private JTextArea arbreToNoeudsTA;
    private JTextArea noeudsFromArbreTA;
    private JButton btnNoeuds;
    private JLabel nbNoeudLabel;
    private JLabel nbFeuilleLabel;
    private JList fileStructList;
    private JTextArea fileSeqTA;
    private JTextField filePathTF;
    private JButton fileSelectBtn;
    private JTextArea fileParTA;
    private JTextArea fileArbreTA;
    private JButton arbreBtn;
    private JTextArea arbreStringTA;
    private JTextArea arbreAffTA;
    private JTextArea compArb1TA;
    private JButton compArbBTN;
    private JTextPane compArbResTA;
    private JTextArea compArb2TA;
    private JButton aleaGenBTN;
    private JTextArea aleaSeqTA;
    private JTextArea aleaApparTA;
    private JTextArea aleaArbreTA;
    private DefaultListModel<Structure> model = new DefaultListModel<>();

    public Screen(){

        super("ARN ") ;
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        DefaultListModel<String> fileModel = new DefaultListModel<>();
        fileStructList.setModel(fileModel);
        structList.setModel(model);

        convToArbre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                HashMap<String, String> resultat = new HashMap<String, String>();
                StringsFormatting formatting = new StringsFormatting();
                resultat = formatting.bienSaisi(seqTextArea.getText() ,apparTextArea.getText());
                if (resultat.get("resultat") != "OK"){
                    arbreTextArea.setText("erreur");
                    JOptionPane.showMessageDialog(panelMain,
                            resultat.get("message"), "Erreur ! ",
                            JOptionPane.WARNING_MESSAGE);
                } else{
                    ArbreService arbCont = new ArbreService();
                    String arbre = arbCont.apparToArbreString(seqTextArea.getText(),apparTextArea.getText());
                    arbreTextArea.setText(arbre);
                }
            }
        });
        convToSeq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ( arbreTextArea.getText().length()==0){
                    JOptionPane.showMessageDialog(panelMain,
                            "Arbre vide" , "Erreur ! ",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String arbre = arbreTextArea.getText();
                    ArbreService arbCont = new ArbreService();
                    ArrayList<String> resultat = new ArrayList<String>();
                    resultat = arbCont.arbreToAppar(arbre);
                    seqTextArea.setText(resultat.get(0));
                    apparTextArea.setText(resultat.get(1));
                }
            }
        });
        addStruc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                StrucutreService stcCont = new StrucutreService();
                ArbreService arbCont = new ArbreService();


                HashMap<String, String> resultat = new HashMap<String, String>();
                StringsFormatting formatting = new StringsFormatting();
                resultat = formatting.bienSaisi(seqComTA.getText(),apparComTA.getText());
                if (resultat.get("resultat") != "OK"){
                    arbreTextArea.setText("erreur");
                    JOptionPane.showMessageDialog(panelMain,

                            resultat.get("message"), "Erreur ! ",
                            JOptionPane.WARNING_MESSAGE);
                } else{
                    Structure strc  = stcCont.CreateStructure(seqComTA.getText(),apparComTA.getText());
                    model.addElement(strc);
                    Arbre ar = arbCont.apparToArbre(seqComTA.getText(),apparComTA.getText());
                    strc.setArbre(ar);
                }



            }
        });
        structList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                Structure st = (Structure) structList.getSelectedValue();
                affCompAT.setText("ID Structure      :   " + st.getIdStructure() +"\n" +
                                  "NB noeuds         :   " + st.getArbre().getNbNoeudsTot()+"\n" +
                                  "NB feuilles       :   "  + st.getArbre().getNbFeuillesTot()
                                    );

            }
        });
        btnComparer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ( seqComTA.getText().length()==0 || apparComTA.getText().length()==0 ){
                    JOptionPane.showMessageDialog(panelMain,
                            "Appariement non saisi" , "Erreur ! ",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    StrucutreService strCont = new StrucutreService();
                    ArrayList<Structure> strList = new ArrayList<>();
                    String resultat;
                    for (int i = 0; i < structList.getModel().getSize(); i++) {
                        strList.add((Structure) structList.getModel().getElementAt(i));
                    }

                    if (!sequenceCheckBox.isSelected()) {
                        resultat = strCont.compareForm(strList);
                    } else {
                        resultat = strCont.compareFormSeq(strList);
                    }
                    resultatLabel.setText(resultat);
                    resultatLabel.setEnabled(true);

                }
            }
        });
        btnNoeuds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ( arbreToNoeudsTA.getText().length()==0){
                    JOptionPane.showMessageDialog(panelMain,
                            "Arbre vide" , "Erreur ! ",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    noeudsFromArbreTA.setText("");
                    ArbreService arbCont = new ArbreService();
                    StringsFormatting formatting = new StringsFormatting();
                    String arbre = arbreToNoeudsTA.getText();
                    arbre = "  " + arbre + "  ";
                    ArrayList<String> listNoeudsString = arbCont.extractNodes(arbre);
                    for (int i = 0; i < listNoeudsString.size(); i++) {
                        noeudsFromArbreTA.append(
                                listNoeudsString.get(i) + "\n\n"

                        );
                    }

                    String appar = arbCont.arbreToAppar(arbre).get(1);
                    nbNoeudLabel.setText(String.valueOf(formatting.occurence(appar, "(".charAt(0))));
                    nbFeuilleLabel.setText(String.valueOf(formatting.occurence(appar, "-".charAt(0))));
                } }
        });
        fileSelectBtn.addActionListener(new ActionListener() {
            int response ;
            File file;
            Scanner fileIn;
            StringsFormatting formatting = new StringsFormatting();
            HashMap<String, String> resultat = new HashMap<String, String>();
            int numSeq = 0 ;
            JFileChooser chooser = new JFileChooser();
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                response = chooser.showOpenDialog(null);
                if(response == JFileChooser.APPROVE_OPTION){
                    file = chooser.getSelectedFile();
                    try {
                        fileIn = new Scanner(file);
                        if (file.isFile()){
                            String parnth =  formatting.fileGetParenth(file);
                            filePathTF.setText(file.getAbsolutePath());
                            while (fileIn.hasNextLine()){
                                String line = fileIn.nextLine();
                                if (line.contains("A") && line.contains("U")
                                   && line.contains("G") && line.contains("C") && line.contains("-")){
                                    line = line.substring(line.lastIndexOf(" ") + 1 , line.length());
                                   resultat = formatting.fileGetSeq(line,parnth);
                                    numSeq++;
                                    String seq = resultat.get("sequence") ;
                                    String parenth = resultat.get("parenth") ;
                                    fileModel.addElement("N°:" + numSeq + "  " + seq
                                                    + "   @ appariements :  " + parenth);
                                }
                            }
                        }
                        fileIn.close();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });
        fileStructList.addListSelectionListener(new ListSelectionListener() {
            ArbreService arbCont = new ArbreService();
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selected = (String) fileStructList.getSelectedValue();
                String seq = selected.substring(selected.indexOf("  ") + 2 ,
                        selected.indexOf("   @") );
                String parenth =  selected.substring(selected.lastIndexOf(": ") + 3 ,
                        selected.length() );
                String arbre = arbCont.apparToArbreString(seq,parenth);
                fileSeqTA.setText(seq);
                fileParTA.setText(parenth);
                fileArbreTA.setText(arbre);
            }
        });
        arbreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( arbreStringTA.getText().length()==0){
                    JOptionPane.showMessageDialog(panelMain,
                            "Arbre vide" , "Erreur ! ",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    ArbreService arbCont = new ArbreService();
                    String arbre = arbreStringTA.getText();
                    ArrayList<String> affichage = arbCont.affArbre(arbre);
                    for (int i = 0; i < affichage.size(); i++) {
                        String level = affichage.get(i);
                        arbreAffTA.append(level + "\n");
                        System.out.println(level + "\n");
                    }


                }}
        });
        compArbBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ( compArb1TA.getText().length()==0 || compArb2TA.getText().length()==0){
                    JOptionPane.showMessageDialog(panelMain,
                            "Arbre vide" , "Erreur ! ",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    ArbreService arbCont = new ArbreService();
                    StringsFormatting formatting = new StringsFormatting();
                    HashMap<String, String> resultat = new HashMap<String, String>();
                    String arbre1 = compArb1TA.getText();
                    String arbre2 = compArb2TA.getText();
                    if (arbre1.indexOf("N0") < 0){
                        arbre1 = formatting.noeudToArbre(arbre1);
                    }
                    if (arbre2.indexOf("N0") < 0){
                        arbre2 = formatting.noeudToArbre(arbre2);
                    }
                    System.out.println("///////////////////////////////////////");
                    System.out.println("arbre 1 = " + arbre1);
                    System.out.println("arbre 2 = " + arbre2);
                    resultat = arbCont.compArbres(arbre1, arbre2);

                    String arbre1AppSeq = formatting.neutralizeNoeuds(arbre1);
                    String arbre2AppSeq = formatting.neutralizeNoeuds(arbre2);
                    ArrayList<String> listNoeudsString1 = arbCont.extractNodes(arbre1);
                    ArrayList<String> listNoeudsString2 = arbCont.extractNodes(arbre2);
                 /*   System.out.println("************ list noeuds 1 ****************************");
                    System.out.println(listNoeudsString1);
                    System.out.println("************ list noeuds 2 ****************************");
                    System.out.println(listNoeudsString2);*/

                    String longestNoeud = " ";
                    for (int i = 0; i < listNoeudsString1.size(); i++) {
                        for (int j = 0; j < listNoeudsString2.size(); j++) {
                            String N1 = formatting.neutralizeNoeuds(listNoeudsString1.get(i));
                            String N2 = formatting.neutralizeNoeuds(listNoeudsString2.get(j));
                       /*     System.out.println("+++++++ i = " + i + " j = " + j  + "++++++");
                            System.out.println("N1 = " + N1);
                            System.out.println("N2 = " + N2); */

                            if (N1.equals(N2)
                                    && N1.length() > longestNoeud.length()) {
                                longestNoeud = listNoeudsString1.get(i);
                            }
                        }
                    }
                    if (longestNoeud == " ") {
                        longestNoeud = "pas de sous arbre en commun";
                    }
                    compArbResTA.cut();
                    appendToPane(compArbResTA, " motif avec sequence \n", Color.RED);
                    appendToPane(compArbResTA, resultat.get("motif avec seq") + "\n", Color.BLUE);
                    appendToPane(compArbResTA, " motif sans sequence \n", Color.RED);
                    appendToPane(compArbResTA, resultat.get("motif sans seq") + "\n", Color.BLUE);
                    appendToPane(compArbResTA, " le plus grand sous arbre commun \n", Color.RED);
                    appendToPane(compArbResTA, longestNoeud + "\n", Color.BLUE);
                    appendToPane(compArbResTA, "---------------------------------\n", Color.red);

                /*appendToPane(compArbResTA, "Stack", Color.DARK_GRAY);
                appendToPane(compArbResTA, "Over", Color.MAGENTA);
                appendToPane(compArbResTA, "flow", Color.ORANGE); */
                } }
        });
        aleaGenBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArbreService arbCnt = new ArbreService();
                String parenth = generParenthAlea();
                String sequence = generSequenceAlea(parenth);
                String arbre = arbCnt.apparToArbreString(sequence,parenth);
                aleaApparTA.setText(parenth);
                aleaSeqTA.setText(sequence);
                aleaArbreTA.setText(arbre);
            }
        });
    }
    //Effectuer des couleurs et choisir la forme de l'ecriture dans l'affichage de comparaison des arbres
    // equivalent à setText() pour les TextArea s
    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
    //Génération de la parenthèse aléatoire
    public String generParenthAlea(){
        //Instanciation de la classe stringsformatting
        StringsFormatting formatting = new StringsFormatting();
        //Instaciatiion de Random
        Random rand = new Random();
        //Déclaration du nombre de feuille randomisé ayant un min 70 et un max 100
        int nbFeuilles = rand.nextInt(30)+70;
        //nombre de tige: min:2 et max:4
        int nbtiges = rand.nextInt(2)+2;
        //longueur de la 1ere tige: min 10 et max 15
        int longeurTige1 =  rand.nextInt(5)+10;
        //Déclaration vide du parenthésage
        String parenth = "";
        //création de charactère ayant pour paramétres '-' et le nombre de fois de cette feuille
        parenth = formatting.creatStringOfChar(nbFeuilles,'-');
        //La tige se compose en deux des parenthèse ouvrante mais aussi des parenthèse fermante
        //création de parenthèse ouvrante
        String tige1b1 = formatting.creatStringOfChar(longeurTige1 , '(');
        //création de parenthèse fermante
        String tige1b2 = formatting.creatStringOfChar(longeurTige1 , ')');
        // la position du parenthèse de la tigeb1 et tigeb2 en donnant en position 0 et longeur-1 respectivement
        parenth = formatting.insertString(parenth,tige1b1,1);
        parenth = formatting.insertString(parenth,tige1b2,parenth.length());

        // boucler le nombre des tiges interierus
        for ( int i = 0 ; i<nbtiges;i++){
            //La longuer de la tige est randomisé et va avoir une longueur minimal 5 et maximal 11
            int longeurTige =  rand.nextInt(6)+5;
            //Création des caractères des tiges (ouvrante et fermante)
            String tigeb1 = formatting.creatStringOfChar(longeurTige , '(');
            String tigeb2 = formatting.creatStringOfChar(longeurTige , ')');
            //Chercher la dernière position de la tige ouvrante et on ajoute un nombre entre 5 et 10
            //i*20: La dernière tige va s'éloigner 20 feuille en pluspour prendre en compte la fermeture des tiges
            int positionStartTige = parenth.lastIndexOf('(') + rand.nextInt(5)+5 + (i * 20);
            int positionEndTige = positionStartTige + rand.nextInt(5)+15 ;
            //On refait le saisi en mettant au parenthésage la position du premier tige de la position du dernier tige
            parenth = formatting.insertString(parenth,tigeb1,positionStartTige +1 );
            parenth = formatting.insertString(parenth,tigeb2,positionEndTige + 1);
        }
        return parenth;
    }
    //Génération de la séquance aléatoire en fonction du parenthésage
    public String generSequenceAlea(String parenth){
        //Déclaration vide de séquence
        String sequence = "";
        //Package qui permet d'avoir paires sous format chaine de caractère
        StringBuilder paires = new StringBuilder();
        //Instanciation de random
        Random rnd = new Random();
        //Les caractères sont sous format de chaine de caractère AUCG
        String characters = "AUCG";
       for ( int i = 0 ; i<parenth.length() ; i++){
            //si le parenthésage est - (feuille), on peut prendre n'importe quel nucléotide aléatoirement
            if (parenth.charAt(i) == '-'){
                char randomChar = characters.charAt(rnd.nextInt(characters.length()));
                //On ajoute ce caractère à la séquence
                sequence = sequence + randomChar;
            //si le parenthésage est ( (début tige), on peut prendre n'importe quel nucléotide aléatoirement
            } else if ( parenth.charAt(i) == '('){
                char randomChar = characters.charAt(rnd.nextInt(characters.length()));
                //On ajoute ce caractère à la séquence
                sequence = sequence + randomChar;
                //Au meme moment il va les déposer dans la liste paires pour pouvoir effectuer son appariement lors de la fermeture de la tige
                paires = paires.append(randomChar);
                // si la parenthèse est fermante ')'
            } else {
                //Prendre le charactère de la liste de sa tige ouvrante et va faire son appariement et le mettre ensuite dans la séquence, ensuite il va effacer ce caratère de la liste jusqu'à ce que ca se vide
                if (paires.charAt(paires.length() - 1) == 'A'){
                    sequence = sequence +"U";
                    paires.deleteCharAt(paires.length() -1 );
                } else  if (paires.charAt(paires.length()-1) == 'U'){
                    sequence = sequence +"A";
                    paires.deleteCharAt(paires.length() -1);
                } else  if (paires.charAt(paires.length()-1) == 'C'){
                    sequence = sequence +"G";
                    paires.deleteCharAt(paires.length()-1);
                } else  if (paires.charAt(paires.length()-1) == 'G'){
                    sequence = sequence +"C";
                    paires.deleteCharAt(paires.length() -1);
                }
            }
        }
        return  sequence;
    }


}
