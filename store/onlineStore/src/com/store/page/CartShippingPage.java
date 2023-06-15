package com.store.page;

import com.store.cart.Cart;
import com.store.member.UserInIt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CartShippingPage extends JPanel {
    Cart mCart;
    JPanel shippingPanel;
    JPanel radioPanel;

    public CartShippingPage(JPanel panel, Cart cart){
        Font ft;
        ft = new Font("함초롬돋움", Font.BOLD, 15);

        setLayout(null);

        Rectangle rect = panel.getBounds();
        System.out.println(rect);
        setPreferredSize(rect.getSize());

        radioPanel = new JPanel();
        radioPanel.setBounds(300,0,700,50);
        radioPanel.setLayout(new FlowLayout());
        add(radioPanel);
        JLabel radioLabel = new JLabel("배송받을 분은 고객정보와 같습니까?");
        radioLabel.setFont(ft);
        JRadioButton radioOk = new JRadioButton("예");
        radioOk.setFont(ft);
        JRadioButton radioNo = new JRadioButton("아니오");
        radioNo.setFont(ft);
        radioPanel.add(radioLabel);
        radioPanel.add(radioOk);
        radioPanel.add(radioNo);

        shippingPanel = new JPanel();
        shippingPanel.setBounds(200,50,700,500);
        shippingPanel.setLayout(null);
        add(shippingPanel);

        radioOk.setSelected(true);
        radioNo.setSelected(false);
        UserShippingInfo(true);

        this.mCart = cart;

        radioOk.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioOk.isSelected()) {
                    shippingPanel.removeAll();
                    UserShippingInfo(true);
                    shippingPanel.revalidate();
                    shippingPanel.repaint();
                    radioNo.setSelected(false);
                }
            }
        });

        radioNo.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioNo.isSelected()) {
                    shippingPanel.removeAll();
                    UserShippingInfo(false);
                    shippingPanel.revalidate();
                    shippingPanel.repaint();
                    radioNo.setSelected(false);
                }
            }
        });
    }
    public void UserShippingInfo(boolean select){
        Font ft;
        ft = new Font("함초롬돋움", Font.BOLD, 15);
        JPanel namePanel = new JPanel();
        namePanel.setBounds(0,100,700,50);
        JLabel nameLabel = new JLabel("고객명 :");
        nameLabel.setFont(ft);
        namePanel.add(nameLabel);

        JTextField nameLabel2 = new JTextField(15);
        nameLabel2.setFont(ft);
        if (select){
            nameLabel2.setBackground(Color.LIGHT_GRAY);
            nameLabel2.setText(UserInIt.getmUser().getName());
        }
        namePanel.add(nameLabel2);
        shippingPanel.add(namePanel);

        JPanel phonePanel = new JPanel();
        phonePanel.setBounds(0,150,700,50);
        JLabel phoneLabel = new JLabel("연락처 :");
        phoneLabel.setFont(ft);
        phonePanel.add(phoneLabel);

        JTextField phoneLabel2 = new JTextField(15);
        phoneLabel2.setFont(ft);
        if (select){
            phoneLabel2.setBackground(Color.LIGHT_GRAY);
            phoneLabel2.setText(String.valueOf(UserInIt.getmUser().getPhone()));
        }
        phonePanel.add(phoneLabel2);
        shippingPanel.add(phonePanel);

        JPanel addressPenel = new JPanel();
        addressPenel.setBounds(0,200,700,50);
        JLabel label = new JLabel("배송지 :");
        label.setFont(ft);
        addressPenel.add(label);
        JTextField addressText = new JTextField(15);
        addressText.setFont(ft);
        addressPenel.add(addressText);
        shippingPanel.add(addressPenel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0,300,700,100);

        JLabel buttonLabel = new JLabel("주문완료");
        buttonLabel.setFont(new Font("함초롬돋움", Font.BOLD, 15));
        JButton orderButton = new JButton();
        orderButton.add(buttonLabel);
        buttonPanel.add(orderButton);
        shippingPanel.add(buttonPanel);

        orderButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioPanel.removeAll();
                radioPanel.revalidate();
                radioPanel.repaint();
                shippingPanel.removeAll();

                shippingPanel.add("주문 배송지", new CartOrderBillPage(shippingPanel,mCart));

                mCart.deleteBook();
                shippingPanel.revalidate();
                shippingPanel.repaint();
            }
        });

    }
}
