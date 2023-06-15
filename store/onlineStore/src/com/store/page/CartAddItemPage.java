package com.store.page;

import com.store.cart.Cart;
import com.store.item.Book;
import com.store.item.BookInIt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CartAddItemPage extends JPanel {
    ImageIcon imageBook;
    int mSelectRow = 0;
    Cart mCart;

    public CartAddItemPage(JPanel panel, Cart cart) {
        Font ft;
        ft = new Font("함초롬돋움", Font.BOLD, 15);

        setLayout(null);

        Rectangle rect = panel.getBounds();
        System.out.println(rect);
        setPreferredSize(rect.getSize());

        mCart = cart;

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(20, 0, 300, 400);
        imageBook = new ImageIcon("./image/item1.png");
        imageBook.setImage(imageBook.getImage().getScaledInstance(250, 300, Image.SCALE_DEFAULT));
        JLabel label = new JLabel(imageBook);
        imagePanel.add(label);
        add(imagePanel);

        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(300, 0, 700, 400);
        add(tablePanel);

        ArrayList<Book> booklist = BookInIt.getmBookList();
        Object[] tableHeader = {"제품ID", "제품명", "가격", "생산자", "제품설명", "분류", "출시일"};
        Object[][] content = new Object[booklist.size()][tableHeader.length];
        for (int i = 0; i < booklist.size(); i++) {
            Book bookitem = booklist.get(i);
            content[i][0] = bookitem.getBookId();
            content[i][1] = bookitem.getName();
            content[i][2] = bookitem.getUnitPrice();
            content[i][3] = bookitem.getProducer();
            content[i][4] = bookitem.getDescription();
            content[i][5] = bookitem.getCategory();
            content[i][6] = bookitem.getReleaseDate();
        }
        JTable bookTable = new JTable(content, tableHeader);
        bookTable.setRowSelectionInterval(0, 0);
        bookTable.getSelectedColumn();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(600, 350));
        jScrollPane.setViewportView(bookTable);
        tablePanel.add(jScrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 400, 1000, 400);
        add(buttonPanel);
        JLabel buttonLabel = new JLabel("장바구니에 담기");
        buttonLabel.setFont(ft);
        JButton addButton = new JButton();
        addButton.add(buttonLabel);
        buttonPanel.add(addButton);

        bookTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = bookTable.getSelectedRow();
                int col = bookTable.getSelectedColumn();
                mSelectRow = row;
                Object value = bookTable.getValueAt(row, 0);
                String str = value + ".png";

                imageBook = new ImageIcon("./images/" + str);
                imageBook.setImage(imageBook.getImage().getScaledInstance(250, 300, Image.SCALE_DEFAULT));
                JLabel label = new JLabel(imageBook);
                imagePanel.removeAll();
                imagePanel.add(label);
                imagePanel.revalidate();
                imagePanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Book> booklist = BookInIt.getmBookList();
                int select = JOptionPane.showConfirmDialog(addButton, "장바구니에 추가하겠습니까?");
                if (select == 0) {
                    int numId = mSelectRow;
                    if (!isCartInBook(booklist.get(numId).getBookId())){
                        mCart.insertBook(booklist.get(numId));
                    }
                    JOptionPane.showMessageDialog(addButton, "추가했습니다");

                }
            }
        });
    }

    public boolean isCartInBook(String bookId) {
        return mCart.isCartInBook(bookId);
    }

}