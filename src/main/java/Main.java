
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static public boolean is_sml(String s1,String s2){
        int size = s1.length(),size2 = s2.length(),count = 0;
        if (size != size2) return false;
        for(int i = 0;i < size;++i){
            if(s1.charAt(i) != s2.charAt(i)) count++;
            if(count >= 2) return false;
        }
        if(count == 1) return true;
        return false;
    }
    static class TreeSpot{
        String word;
        ArrayList<TreeSpot> son;
        TreeSpot farther;
        boolean searched;
        boolean is_son;
        TreeSpot(String s){
            is_son = false;
            searched = false;
            word = s;
            farther = null;
            son = new ArrayList<TreeSpot>();
        }
    }

    static class WordDictionary{
        ArrayList<ArrayList<TreeSpot> > dic = new ArrayList<ArrayList<TreeSpot> >();
        ArrayList<Integer> word_len = new ArrayList<Integer>();
        void push(String s){
            //System.out.println("123");
            if(s == null) return;
            int size = s.length();
            int num = word_len.size();
            int sub = 0;
            boolean flag = false;
            //System.out.println("345");
            for(sub = 0;sub < num;++sub){
                if((int)word_len.get(sub) == size) {
                    flag = true;
                    break;
                }
            }
            //System.out.println("123");
            if(!flag){
                word_len.add(size);
                ArrayList<TreeSpot> a = new ArrayList<TreeSpot>();
                dic.add(a);
                dic.get(sub).add(new TreeSpot(s));
                return;
            }
            int num_of_word = dic.get(sub).size();
            TreeSpot new_word = new TreeSpot(s);

            dic.get(sub).add(new_word);
            return;

        }
        void build(int len ){
            int size = word_len.size(),sub = 0;
            if(len == 0){
                for(sub = 0;sub < size;sub++){
                    int num_of_word = dic.get(sub).size();
                    TreeSpot temp1,temp2;
                    for(int i = 0;i < num_of_word;++i){
                        for(int j = i + 1;j < num_of_word;++j){
                            temp1 = dic.get(sub).get(i);
                            temp2 = dic.get(sub).get(j);
                            if(is_sml(temp1.word,temp2.word)){
                                temp1.son.add(temp2);
                                temp2.son.add(temp1);
                            }
                        }
                    }
                }
                return;
            }
            for(int i = 0;i < size;i++){
                if((int)word_len.get(i) == len){
                    sub = i;
                    break;
                }
            }
            int num_of_word = dic.get(sub).size();
            TreeSpot temp1,temp2;
            for(int i = 0;i < num_of_word;++i){
                for(int j = i + 1;j < num_of_word;++j){
                    temp1 = dic.get(sub).get(i);
                    temp2 = dic.get(sub).get(i);
                    if(is_sml(temp1.word,temp2.word)){
                        temp1.son.add(temp2);
                        temp2.son.add(temp1);
                    }
                }
            }
        }
        void build(TreeSpot ptr){
            if (ptr.son.size() != 0) return;
            int size = ptr.word.length(),sub = 0,nums = word_len.size();
            for(int i = 0;i < nums;i++){
                if((int)word_len.get(i) == size){
                    sub = i;
                    break;
                }
            }
            int num = dic.get(sub).size();
            TreeSpot temp;
            for(int i = 0;i < num ;i++){
                temp = dic.get(sub).get(i);
                if(temp.searched || temp.is_son) continue;

                if(is_sml(temp.word,ptr.word)){
                    ptr.son.add(temp);
                }
            }
        }
        TreeSpot find(String s){
            int size = s.length();
            int sub = 0;
            while((int)word_len.get(sub) != size) sub++;
            int num = dic.get(sub).size();
            TreeSpot temp;
            for(int i = 0;i < num ;i++){
                temp = dic.get(sub).get(i);
                //System.out.println(temp.word + " :: " + s);
                if(temp.word.equals(s)) {
                    //System.out.println("find!!!\n\n\n");
                    return temp;
                }
            }
            return null;
        }

    }

    static class NodePtr{
        TreeSpot value;
        NodePtr next;
        NodePtr(TreeSpot spot){
            value = spot;
            next = null;
        }
    }

    static class Queue_word{
        int size = 0;
        NodePtr head = null;
        NodePtr tail = null;
        void clear(){
            //TreeSpot temp;
            //while(size != 0){
            //    pop(temp);
            //}
        }
        Queue_word(){
            head = tail = null;
            size = 0;
        }
        void push(TreeSpot spot){
            //System.out.println("pushing !!!!");
            NodePtr ptr = new NodePtr(spot);
            //System.out.println("push !!!!");
            //System.out.println("push word: " + spot.word);
            if(size == 0){
                head = tail = ptr;
                size++;
                //System.out.println(ptr.value.word);
                //System.out.println("size is " + size);
                return;
            }
            tail.next = ptr;
            tail = ptr;
            size++;
            //System.out.println("size is " + size);
            return;
        }
        TreeSpot pop(){
            if(size == 0) {
                System.out.println("pop failure");
                return new TreeSpot("");
            }

            TreeSpot temp = head.value ;
            //System.out.println("pop word:  "+temp.word);
            head = head.next;
            if(head == null){
                tail = head;
            }
            size--;
            return temp;
        }
    }
    static WordDictionary dictionary;
    static Queue_word queue;
    void clear() {
        for(int i = 0;i < dictionary.dic.size();i++){
            int size = dictionary.dic.get(i).size();
            for(int j = 0;j < size;j++){
                TreeSpot temp = dictionary.dic.get(i).get(j);
                temp.farther = null;
                temp.is_son = false;
                temp.searched = false;
            }
        }
    }
    public static void main(String[] args) {
        //Map string_map = new HashMap();
        //Vector s = new Vector();
        Scanner scan = new Scanner(System.in);
        dictionary = new WordDictionary();
        queue = new Queue_word();
        System.out.println("Dictionary file name? ");
        String filename = scan.nextLine();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./target/classes/" + filename));;
        }catch(IOException e1){
            System.out.println("no file");
        }
        String s = "123";
        while(s != null){
            try {
                s = br.readLine();
            }catch(IOException el){
                System.out.println("error");
            }
            //System.out.println(s);
            if(s == null) break;
            dictionary.push(s);
        }
        boolean done = false;
        String word1,word2;
        System.out.println("input 2 words");
        word1 = scan.nextLine();
        word2 = scan.nextLine();
        while(/*input(word1,word2)*/ true){
            //cout << word1 << "  " << word2 << endl;
            if(word1.equals("") || word2.equals("")){
                System.out.println("Have a nice day");
                //return 0;
                break;
            }
            done = false;
            //if(!dictionary.find(word1)) dictionary.push(word1);
            if((dictionary.find(word1) == null) || (dictionary.find(word2) == null)){
                System.out.println("The two words must be found in the dictionary.");
                continue;
            }
            TreeSpot root = dictionary.find(word1),temp = new TreeSpot(""),son;
            //System.out.println(root.word);
            root.is_son = true;
            //System.out.println(root.word);
            queue.push(root);
            int num = dictionary.word_len.size();

            while(true){
                temp = queue.pop();
                if(temp.word == "") break;
                if(temp.word.equals(word2)){
                    done = true;
                    break;
                }
                if(temp.searched) continue;
                temp.searched = true;
                dictionary.build(temp);
                //System.out.println(temp.word);
                int size = temp.son.size();
                //System.out.println(temp.word + "'s size is " + size );
                for(int i = 0;i < size;i++){
                    son = temp.son.get(i);
                    if(son.is_son)continue;
                    son.farther = temp;
                    //System.out.println(son.word + "farther is " + temp.word);
                    //cout << son->word << " farther is " << temp->word << endl;
                    son.is_son = true;
                    queue.push(son);
                }
            }
            if(!done) System.out.println("No word ladder found from " + word2 + " back to " + word1 + ".\n"); //cout << "No word ladder found from " << word2 << " back to " << word1 << "."<< endl << endl;
            else {
                System.out.println("A ladder from " + word2 + " back to " + word1 + ":\n");
                //cout << "A ladder from " << word2 << " back to " << word1 << ":"<<endl;
            }
            int a = 0;
            while((temp != null) && done){
                System.out.println(temp.word + " ");
                //cout << temp.word << " ";
                temp = temp.farther;
                a++;
            }
            //cout << a << endl;
            if(temp == null) System.out.println("\n\n"); //cout << endl << endl;
            queue.clear();
            //clear();
            break;
        }
        //System.out.println("hello world!");
    }
}
