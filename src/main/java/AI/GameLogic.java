package AI;

import Model.GameData;
import Model.GamePlay;
import Model.Poker;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;


public class GameLogic {
    private GameData gameData;
    private int[][] card = new int[4][15];//黑桃、梅花、红桃、方块
    private int colorcounst;
    private int[] color = new int[4];
    private int[] digital = new int[15];
    private boolean isGreater;
    private boolean isLess;

    public GameLogic(GameData gameData){
        this.gameData = gameData;
        getCard();
    }

    private int getColor(char s){
        if(s == '$') return 0;
        if(s == '*') return 1;
        if(s == '&') return 2;
        return 3;
    }

    private int getDig(char s){
        if(s == 'A') return 13;
        if(s == 'K') return 12;
        if(s == 'Q') return 11;
        if(s == 'J') return 10;
        return s - '0' - 1;
    }

    private void getCard() {
        isGreater = isLess = true;
        int n = gameData.getStrCard().length();
        for(int i = 0;i < n;){
            if(i != 0) i++;
            int r = 2;
            if(i + 2 < n && gameData.getStrCard().charAt(i+2) == '0') r++;
            String t = gameData.getStrCard().substring(i, i+r);
            int c = getColor(t.charAt(0));
            int d = getDig(t.charAt(1));
            if(r == 3) d = 9;
            card[c][d]++;
            if(color[c] == 0) colorcounst++;
            color[c]++;
            digital[d]++;
            if(d < 8) isGreater = false;
            if(d >= 8) isLess = false;
            i += r;
        }
    }

    //----------------------特殊牌型---------------------------

    //龙
    private boolean isDragon(){
        boolean[] chk = new boolean[15];
        for(int i = 1;i <= 13;i++){
            if(digital[i] > 1) return false;
        }
        return true;
    }

    //三分
    private boolean isThreeBoom(){
        int chk = 0;
        for(int j = 1;j <= 13;j++){
            if(digital[j] >= 4) chk++;
        }
        if(chk == 3) return true;
        return false;
    }

    //全大小/全同花
    private boolean isAllBest(){
        return isLess || isGreater || colorcounst == 13;
    }

    //凑一色
    private boolean isTwoColor(){
        return (color[0]+color[2] == 13 || color[1]+color[2] == 13);
    }

    //双怪冲三/四套三条/五队三条/六对半
    private boolean isTwoThree(int two, int three, int j){
        if(j == 14){
            return (two == 3 && three == 2)||(three == 4)||(two == 5 && three == 1)||(two == 6);
        }
        if(digital[j] == 2) return isTwoThree(two+1, three, j+1);
        if(digital[j] == 3) return (isTwoThree(two+1, three, j+1) || isTwoThree(two, three+1, j+1));
        if(digital[j] == 4) return (isTwoThree(two+2, three, j+1) || isTwoThree(two, three+1, j+1));
        return isTwoThree(two, three, j+1);
    }

    //三顺子
    private boolean isThreeStraight(int[][] tmp, int op, int s, int t){
        if(op == 4){
            return s == 13;
        }
        for(int i = 0;i < 4;i++){
            for(int j = 1;j <= 13-t;j++){
                if(tmp[i][j] >= 1){
                    boolean chk = true;
                    for(int k = 0;k < t;k++)
                        if(tmp[i][j+k] == 0) chk = false;
                    if(chk){
                        int[][] tmps = new int[4][];
                        for (int o = 0;o < 4;o++)
                            tmps[o] = tmp[o].clone();
                        for(int k = 0;k < t;k++)
                            tmps[i][j+k]--;
                        if(isThreeStraight(tmps, op+1, s+t, 5)) return true;
                    }
                }
            }
        }
        return false;
    }

    //----------------------普通牌型---------------------------

    //判断有效性
    private boolean isValue(Poker front, Poker middle, Poker back){
        return back.getRank()>=middle.getRank() && middle.getRank()>=front.getRank();
    }

    //权值阵列
    int[][] scoreBase = {
            { 0, 0,  0,  0,  0,  0,  1,  1,  2,  2,  4,  7, 14, 33},
            { 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  1},
            { 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}
    };

    int[][] score20 = {
            { 0, 46, 48, 50, 51, 54, 56, 60, 63, 68, 74, 81, 89, 97},
            { 0, 2,  3,  4,  4,  5,  7,  8, 10, 12, 15, 19, 24, 33},
            { 0, 0,  0,  0,  0,  0,  0,  0,  0,  1,  1,  1,  2,  3}
    };

    int[][] score22 = {
            { 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
            { 0, 0, 36, 37, 38, 40, 44, 46, 49, 54, 57, 62, 64,  0 },
            { 0, 0,  2,  3,  4,  4,  6,  7,  8, 10, 11, 13, 13,  0 }
    };

    int[][] score30 = {
            { 0, 99, 99,100,100,100,100,100,100,100,100,100,100,100 },
            { 0, 63, 65, 69, 71, 72, 73, 73, 73, 74, 74, 75, 75, 75 },
            { 0, 11, 12, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15 }
    };

    int[][] scoresz = {
            { 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
            { 0, 0,  0,  0, 77, 78, 81, 83, 85, 87, 88, 90, 91, 92 },
            { 0, 0,  0,  0, 16, 17, 20, 22, 24, 26, 28, 32, 33, 36 }
    };

    int[][] scoresc = {
            { 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
            { 0, 0,  0,  0,  0,  0, 93, 93, 93, 93, 94, 95, 97, 98 },
            { 0, 0,  0,  0,  0,  0, 36, 36, 37, 38, 40, 44, 49, 61 }
    };

    int[][] score32 = {
            { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
            { 0, 98, 98, 99, 99, 99,100,100,100,100,100,100,100,100 },
            { 0, 64, 67, 70, 71, 73, 75, 77, 80, 82, 85, 88, 91, 94 }
    };

    int[][] score40 = {
            { 0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
            { 0, 100,100,100,100,100,100,100,100,100,100,100,100,100 },
            { 0,  93, 94, 95, 95, 96, 96, 96, 97, 97, 98, 98, 98, 98 }
    };

    int[][] scoreszc = {
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
            {  0,  0,  0,  0,100,100,100,100,100,100,100,100,100,  0 },
            {  0,  0,  0,  0, 98, 98, 99, 99, 99, 99, 99, 99,100,  0 }
    };

    //比较两个结果的大小
    private GamePlay max(GamePlay newer, GamePlay older){
        if(older.getCard().size() == 0) return newer;
        if(newer == null) return older;
        int[] sum = new int[2];
        for(int op = 0;op < 2;op++){
            GamePlay tmp = null;
            if(op == 0) tmp = newer;
            else tmp = older;
            for(int i = 0;i < 3;i++){
                Poker x = tmp.getCard().get(i);
                int y = (int)(x.getRank()/Type.First)%50;
                if(x.getRank() >= Type.Tyszc) sum[op] += scoreszc[i][y];
                else if(x.getRank() >= Type.Ty40) sum[op] += score40[i][y];
                else if(x.getRank() >= Type.Ty32) sum[op] += score32[i][y];
                else if(x.getRank() >= Type.Tysc) sum[op] += scoresc[i][y];
                else if(x.getRank() >= Type.Tysz) sum[op] += scoresz[i][y];
                else if(x.getRank() >= Type.Ty30) sum[op] += score30[i][y];
                else if(x.getRank() >= Type.Ty22) sum[op] += score22[i][y];
                else if(x.getRank() >= Type.Ty20) sum[op] += score20[i][y];
                else sum[op] += scoreBase[i][y];
            }
        }
        if(sum[0] > sum[1]) return newer;
        return older;
    }

    private void Base(int[][] card, ArrayList<Poker> res){
        for(int j = 13;j >= 1;j--){
            for(int i = 0;i < 4;i++) {
                if(card[i][j] == 0) continue;
                Poker t = new Poker(Type.Base+j*Type.First);
                t.add(i, j);
                res.add(t);
            }
        }
    }

    //对子
    private boolean is20(int[] digital, int[][] card, ArrayList<Poker> res){
        boolean chk = false;
        for(int j = 13;j >= 1;j--) {
            if (digital[j] >= 2) {
                chk = true;
                for (int i = 0; i < 4; i++) {
                    if(card[i][j] != 1) continue;
                    for(int k = i+1;k < 4;k++) {
                        if(card[k][j] != 1) continue;
                        Poker t = new Poker(Type.Ty20 + j*Type.First);
                        t.add(i, j);
                        t.add(k, j);
                        res.add(t);
                    }
                }
            }
        }
        return chk;
    }

    //两对
    private boolean is22(int li1, int li2, int lj, int[] digital, int[][] card, ArrayList<Poker> res){
        boolean chk = false;
        if(lj != 0){
            digital[lj] -= 2;
            card[li1][lj] = card[li2][lj] = 0;
            for(int j = lj;j >= 1;j--){
                if(digital[j] >= 2){
                    chk = true;
                    for(int i1 = 0;i1 < 4;i1++){
                        if(card[i1][j] == 0) continue;
                        for(int i2 = i1+1;i2 < 4;i2++){
                            if(card[i2][j] == 0) continue;
                            Poker t;
                            if(lj-j == 1)t = new Poker(Type.Ty221 + lj*Type.First + j*Type.Second);
                            else t = new Poker(Type.Ty22 + lj*Type.First + j*Type.Second);
                            t.add(li1, lj);t.add(li2, lj);
                            t.add(i1, j);t.add(i2, j);
                            res.add(t);
                        }
                    }
                }
            }
            card[li1][lj] = card[li2][lj] = 1;
            digital[lj] += 2;
            return chk;
        }
        for(int j = 13;j >= 1;j--){
            if(digital[j] >= 2) {
                for(int i1 = 0;i1 < 4;i1++) {
                    if(card[i1][j] != 1) continue;
                    for(int i2 = i1+1;i2 < 4;i2++){
                        if(card[i2][j] != 1) continue;
                        chk = chk|is22(i1, i2, j, digital, card, res);
                    }
                }
            }
        }
        return chk;
    }

    //三条
    private boolean is30(int[] digital, int[][] card, ArrayList<Poker> res){
        boolean chk = false;
        for(int j = 13;j >= 1;j--) {
            if (digital[j] >= 3) {
                chk = true;
                for (int i = 0; i < 4; i++) {
                    if(card[i][j] != 1) continue;
                    for(int k = i+1;k < 4;k++){
                        if(card[k][j] != 1) continue;
                        for(int l = k+1;l < 4;l++){
                            if(card[l][j] != 1) continue;
                            Poker t = new Poker(Type.Ty30 + j*Type.First);
                            t.add(i, j);
                            t.add(k, j);
                            t.add(l, j);
                            res.add(t);
                        }
                    }
                }
            }
        }
        return chk;
    }

    //顺子
    private boolean isSZ(int n, int j, int[][] card, ArrayList<Poker> res, Poker t){
        if(n == 5){
            boolean chk = true;
            for(int i = 1;i < n;i++){
                if(t.getPk().get(i).getKey() != t.getPk().get(i-1).getKey()) chk = false;
            }
            if(!chk)
                res.add(t);
            return true;
        }
        if(j == 0) return false;
        boolean chk = false;
        for(int i = 0;i < 4;i++)
            if(card[i][j] != 0) {
                Poker tt;
                if(t == null) tt = new Poker(Type.Tysz + j*Type.First);
                else tt = t.clone();
                tt.add(i, j);
                chk = chk|isSZ(n+1, j-1, card, res, tt);
            }
        if(n == 0) chk = chk|isSZ(n, j-1, card, res, t);
        return chk;
    }

    //同花
    private void dfsSC(int n, int i, int j, int[][] card, ArrayList<Poker> res, Poker t){
        if(n == 5){
            int[] dig = new int[15];
            for(int k = 0;k < 5;k++) dig[t.getPk().get(i).getValue()]++;
            int chk = 0, s = 0;
            for(int k = 13;k >= 1;k--){
                if(chk >= 0 && dig[i] != 0) {
                    if(chk == 0) t.addRank(k);
                    chk = 1; s++;
                }
                if(chk == 1 && dig[i] == 0) chk = -1;
            }
            if(s != 5)
                res.add(t);
            return;
        }
        if(j == 0) return;
        Poker tt = null;
        if(t != null) tt = t.clone();
        else tt = new Poker(Type.Tysc);
        int tn = n;
        if(card[i][j] == 1) {
            tt.add(i, j);
            tt.addRank(j);
            tn++;
            card[i][j] = 0;
            dfsSC(tn, i, j + 1, card, res, tt);
            card[i][j] = 1;
        }
        dfsSC(n, i, j-1, card, res, t);
    }
    private boolean isSC(int[] color, int[][] card, ArrayList<Poker> res){
        boolean chk = false;
        for(int i = 0;i < 4;i++){
            if(color[i] >= 5){
                chk = true;
                dfsSC(0, i, 13, card, res, null);
            }
        }
        return chk;
    }

    //葫芦
    private boolean is32(int[] digital, int[][] card, ArrayList<Poker> res){
        boolean chk = false;
        for(int j1 = 13;j1 >= 1;j1--){
            if(digital[j1] >= 3){

                for(int i1 = 0;i1 < 4;i1++){
                    if(card[i1][j1] == 0) continue;

                    for(int i2 = i1+1;i2 < 4;i2++){
                        if(card[i2][j1] == 0) continue;

                        for(int i3 = i2+1;i3 < 4;i3++){
                            if(card[i3][j1] == 0) continue;

                            for(int j2 = 13;j2 >= 1;j2--){
                                if(j1 == j2) continue;
                                if(digital[j2] >= 2){

                                    for(int i11 = 0;i11 < 4;i11++){
                                        if(card[i11][j2] == 0) continue;

                                        for(int i22 = i11+1;i22 < 4;i22++){
                                            if(card[i22][j2] == 0) continue;
                                            chk = true;
                                            Poker t = new Poker(Type.Ty32 + j1*Type.First);
                                            t.add(i1, j1);t.add(i2,j1);t.add(i3, j1);
                                            t.add(i11, j2);t.add(i22, j2);
                                            res.add(t);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return chk;
    }

    //炸弹
    private boolean is40(int[] digital, int[][] card, ArrayList<Poker> res){
        boolean chk = false;
        for(int j = 13;j >= 1;j--) {
            if (digital[j] == 4) {
                chk = true;
                Poker t = new Poker(Type.Ty40 + j*Type.First);
                for (int k = 0; k < 4; k++) t.add(k, j);
                res.add(t);
            }
        }
        return chk;
    }

    //同花顺
    private boolean dfsSZC(int n, int i, int j, int[][] card, ArrayList<Poker> res, Poker t){
        if(n == 5){
            res.add(t);
            return true;
        }
        if(j == 0) return false;
        boolean chk = false;
        if(card[i][j] >= 1) {
            Poker tt;
            if(t == null) tt = new Poker(Type.Tyszc+j*Type.First);
            else tt = t.clone();
            tt.add(i, j);
            chk = dfsSZC(n+1, i, j-1, card, res, tt);
        }
        if(n == 0) chk = chk|dfsSZC(n, i, j-1, card, res, t);
        return chk;
    }
    private boolean isSZC(int[] color, int[][] card, ArrayList<Poker> res){
        boolean chk = false;
        for(int i = 0;i < 4;i++){
            if(color[i] >= 5){
                chk = chk|dfsSZC(0, i, 13, card, res, null);
            }
        }
        return chk;
    }

    private ArrayList<Poker> getBack(){
        ArrayList<Poker> res = new ArrayList<Poker>();
        isSZC(color, card, res);
        is40(digital, card, res);
        is32(digital, card, res);
        isSC(color, card, res);
        isSZ(0, 13, card, res, null);
        is30(digital, card, res);
        is22(0, 0, 0, digital, card, res);
        is20(digital, card, res);
        return res;
    }

    private ArrayList<Poker> getMiddle(Poker back){
        int[][] tmp = new int[4][];
        for(int i = 0;i < 4;i++) tmp[i] = card[i].clone();
        int[] color = this.color.clone();
        int[] digital = this.digital.clone();
        for(int i = 0;i < back.getPk().size();i++) {
            tmp[back.getPk().get(i).getKey()][back.getPk().get(i).getValue()]--;
            color[back.getPk().get(i).getKey()]--;
            digital[back.getPk().get(i).getValue()]--;
        }
        ArrayList<Poker> res = new ArrayList<Poker>();
        if(back.getRank() >= Type.Tyszc) isSZC(color, tmp, res);
        if(back.getRank() >= Type.Ty40) is40(digital, tmp, res);
        if(back.getRank() >= Type.Ty32) is32(digital, tmp, res);

        if(back.getRank() >= Type.Tysc) isSC(color, tmp, res);
        if(back.getRank() >= Type.Tysz) isSZ(0, 13, tmp, res, null);
        if(back.getRank() >= Type.Ty30) is30(digital, tmp, res);
        if(back.getRank() >= Type.Ty22) is22(0, 0, 0, digital, tmp, res);
        if(back.getRank() >= Type.Ty20) is20(digital, tmp, res);
        if(back.getRank() >= Type.Base) Base(tmp, res);

        return res;
    }

    private ArrayList<Poker> getMiddle(Poker front, Poker back){
        int[][] tmp = new int[4][];
        for(int i = 0;i < 4;i++) tmp[i] = card[i].clone();
        int[] color = this.color.clone();
        int[] digital = this.digital.clone();
        for(int i = 0;i < back.getPk().size();i++) {
            tmp[back.getPk().get(i).getKey()][back.getPk().get(i).getValue()]--;
            color[back.getPk().get(i).getKey()]--;
            digital[back.getPk().get(i).getValue()]--;
        }
        for(int i = 0;i < front.getPk().size();i++) {
            tmp[front.getPk().get(i).getKey()][front.getPk().get(i).getValue()]--;
            color[front.getPk().get(i).getKey()]--;
            digital[back.getPk().get(i).getValue()]--;
        }
        ArrayList<Poker> res = new ArrayList<Poker>();
        if(front.getRank() <= Type.Tyszc && back.getRank() >= Type.Tyszc) isSZC(color, tmp, res);
        if(front.getRank() <= Type.Ty40 && back.getRank() >= Type.Ty40) is40(digital, tmp, res);
        if(front.getRank() <= Type.Ty32 && back.getRank() >= Type.Ty32) is32(digital, tmp, res);
        if(front.getRank() <= Type.Tysc && back.getRank() >= Type.Tysc) isSC(color, tmp, res);
        if(front.getRank() <= Type.Tysz && back.getRank() >= Type.Tysz) isSZ(0, 13, tmp, res, null);
        if(front.getRank() <= Type.Ty30 && back.getRank() >= Type.Ty30) is30(digital, tmp, res);
        if(front.getRank() <= Type.Ty22 && back.getRank() >= Type.Ty22) is22(0, 0, 0, digital, tmp, res);
        if(front.getRank() <= Type.Ty20 && back.getRank() >= Type.Ty20) is20(digital, tmp, res);
        Base(tmp, res);
        if(res.size() == 0) return null;
        return res;
    }

    private ArrayList<Poker> getFront(Poker back){
        int[][] tmp = new int[4][];
        for(int i = 0;i < 4;i++) tmp[i] = card[i].clone();
        int[] color = this.color.clone();
        int[] digital = this.digital.clone();
        for(int i = 0;i < back.getPk().size();i++) {
            tmp[back.getPk().get(i).getKey()][back.getPk().get(i).getValue()]--;
            color[back.getPk().get(i).getKey()]--;
            digital[back.getPk().get(i).getValue()]--;
        }
        ArrayList<Poker> res = new ArrayList<Poker>();
        if(back.getRank() >= Type.Ty30) is30(digital, tmp,res);
        if(back.getRank() >= Type.Ty20) is20(digital, tmp,res);
        Base(tmp, res);
        return res;
    }

    private ArrayList<Poker> getFront(Poker middle, Poker back){
        int[][] tmp = new int[4][];
        for(int i = 0;i < 4;i++) tmp[i] = card[i].clone();
        int[] color = this.color.clone();
        int[] digital = this.digital.clone();
        for(int i = 0;i < back.getPk().size();i++) {
            tmp[back.getPk().get(i).getKey()][back.getPk().get(i).getValue()]--;
            color[back.getPk().get(i).getKey()]--;
            digital[back.getPk().get(i).getValue()]--;
        }
        for(int i = 0;i < middle.getPk().size();i++) {
            tmp[middle.getPk().get(i).getKey()][middle.getPk().get(i).getValue()]--;
            color[middle.getPk().get(i).getKey()]--;
            digital[middle.getPk().get(i).getValue()]--;
        }
        ArrayList<Poker> res = new ArrayList<Poker>();
        if(middle.getRank() >= Type.Ty30 && back.getRank() >= Type.Ty30) is30(digital, tmp,res);
        if(middle.getRank() >= Type.Ty20 && back.getRank() >= Type.Ty20) is20(digital, tmp,res);
        Base(tmp, res);
        return res;
    }

    private boolean fixPoker(Poker front, Poker middle, Poker back) {
        int[][] tmp = new int[4][];
        for(int i = 0;i < 4;i++) tmp[i] = card[i].clone();
        for (int i = 0; i < back.getPk().size(); i++) {
            tmp[back.getPk().get(i).getKey()][back.getPk().get(i).getValue()]--;
        }
        for (int i = 0; i < middle.getPk().size(); i++) {
            tmp[middle.getPk().get(i).getKey()][middle.getPk().get(i).getValue()]--;
        }
        for (int i = 0; i < front.getPk().size(); i++) {
            tmp[front.getPk().get(i).getKey()][front.getPk().get(i).getValue()]--;
        }
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        for (int j = 13; j >= 1; j--) {
            for (int i = 0; i < 4; i++) {
                for(int k = 0;k < tmp[i][j];k++)
                q.add(new Pair<>(i, j));
            }
        }
        boolean flag = false; int n = 0;
        while(front.getPk().size() < 3){
            if(n == q.size()) return false;
            Pair<Integer, Integer> t = new Pair<>(q.element().getKey(), q.poll().getValue());
            flag = false;
            for(int i = 0;i < front.getPk().size();i++){
                if(front.getPk().get(i).getValue().equals(t.getValue())){
                    flag = true;n++;break;
                }
            }
            if(flag) q.add(t);
            else
                front.add(t.getKey(), t.getValue());
        }
        while (middle.getPk().size() < 5){
            middle.add(q.element().getKey(), q.poll().getValue());
        }
        while (back.getPk().size() < 5){
            back.add(q.element().getKey(), q.poll().getValue());
        }
        return true;
    }

    public GamePlay playing(){
        GamePlay ans = new GamePlay(gameData.getGID());
        if(isDragon() || isAllBest() || isThreeBoom() || isTwoColor() ||
                isTwoThree(0,0,1) || isThreeStraight(card,1,0,3)) {
            int n = 0;
            Poker t = new Poker(Type.Tymaster);
            ans = new GamePlay(gameData.getGID());
            for (int i = 0; i < 4; i++)
                for (int j = 1; j <= 13; j++) {
                    if(card[i][j] == 1){
                        t.add(i, j);
                    }
                    if((n == 0 && t.getPk().size() == 3) || (t.getPk().size() == 5)){
                        ans.add(t);
                        n++;
                        t = new Poker(Type.Tymaster);
                    }
                }
            System.out.println("supper");
            for(int i = 0;i < 3;i++) ans.add(ans.getCard().get(i).toString());
            return ans;
        }

        ArrayList<Poker> back = getBack();
        for (int i = 0;i < back.size();i++){
            Poker tback = back.get(i);
            ArrayList<Poker> middle = getMiddle(tback);
            for(int j = 0;j < middle.size();j++){
                Poker tmiddle = middle.get(j);
                ArrayList<Poker> front = getFront(tmiddle, tback);
                for(int k = 0;k < front.size();k++){
                    Poker tfront = front.get(k);
                    if (isValue(tfront, tmiddle, tback)) {
                        Poker ttfront = tfront.clone();
                        Poker ttmiddle = tmiddle.clone();
                        Poker ttback = tback.clone();
                        fixPoker(ttfront, ttmiddle, ttback);
                        ans = max(new GamePlay(gameData.getGID(), ttfront, ttmiddle, ttback), ans);
                    }
                }
            }
        }
        for(int i = 0;i < 3;i++) ans.add(ans.getCard().get(i).toString());
        return ans;
    }
}
