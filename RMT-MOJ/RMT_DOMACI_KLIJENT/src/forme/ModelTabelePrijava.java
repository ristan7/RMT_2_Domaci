/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.NacinPrevoza;
import model.Prijava;

/**
 *
 * @author Korisnik
 */
public class ModelTabelePrijava extends AbstractTableModel {

    private List<Prijava> prijave;
    private String[] kolone = {"Datum ulaska", "Datum izlaska", "Zemlja", "Nacin prevoza", "Status"};

    public ModelTabelePrijava(List<Prijava> prijave) {
        this.prijave = prijave;
    }

    @Override
    public int getRowCount() {
        return prijave.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prijava p = prijave.get(rowIndex);
        Date trenutniDatum = new Date();
        switch (columnIndex) {
            case 0:
                return p.getDatumUlaska();
            case 1:
                return p.getDatumIzlaska();
            case 2:
                return p.getZemlja().getNaziv();
            case 3:
                return p.getNacinPrevoza() + "";
            case 4:
                return vratiStatusPrijave(p, trenutniDatum);
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public String vratiStatusPrijave(Prijava prijava, Date trenutniDatum) {
        long vremeUlaska = prijava.getDatumUlaska().getTime();
        long vremeIzlaska = prijava.getDatumIzlaska().getTime();
        long trenutnoVreme = trenutniDatum.getTime();

        if (vremeIzlaska < trenutnoVreme) {
            return "ZAVRŠENA";
        } else if (vremeUlaska - trenutnoVreme > 48 * 60 * 60 * 1000) {
            return "U OBRADI";
        } else {
            return "ZAKLJUČANA";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Prijava p = prijave.get(rowIndex);
        Date trenutniDatum = new Date();
        if (!vratiStatusPrijave(p, trenutniDatum).equals("U OBRADI")) {
            JOptionPane.showMessageDialog(null, "Jedino mozete izmeniti putovanje koje ima status U OBRADI", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (rowIndex) {
            case 0:
                p.setDatumUlaska((Date) aValue);
                break;
            case 1:
                p.setDatumIzlaska((Date) aValue);
                break;
            case 2:
                p.getZemlja().setNaziv((String) aValue);
                break;
            case 3:
                p.setNacinPrevoza((NacinPrevoza) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        Prijava p = prijave.get(rowIndex);
        Date trenutniDatum = new Date();
        return vratiStatusPrijave(p, trenutniDatum).equals("U OBRADI");
    }

    public List<Prijava> getPrijave() {
        return prijave;
    }

    public void setPrijave(List<Prijava> prijave) {
        this.prijave = prijave;
    }

}
