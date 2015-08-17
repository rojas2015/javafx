/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnt.control;

import hnt.model.Pan;
import java.util.Stack;

/**
 *
 * @author yang
 */
public class Logic {

    public void setNewPole(Stack<Pan> pole, int capcity) {
        int i = capcity - 1;
        while (i >= 0) {
            pole.push(new Pan("p" + i, i * 20 + 100));
            i--;
        }
    }

    public int MovePan(Stack<Pan> soruce, Stack<Pan> target) {

        if (soruce.size() == 0) {
            return 0;
        }

        if (target.size() == 0) {
            target.push(soruce.pop());
            return 1;
        } else {
            Pan pans = soruce.peek();
            Pan pant = target.peek();
            if (CheckSize(pans, pant)) {
                target.push(soruce.pop());
                return 1;
            }
            return 0;
        }

    }

    private boolean CheckSize(Pan pans, Pan pant) {
        boolean canMove = false;

        if (pans.getPsize() < pant.getPsize()) {
            canMove = true;
        }
        return canMove;
    }

}
