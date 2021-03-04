package apractice.class08;

import sun.tracing.dtrace.DTraceProviderFactory;

import java.util.HashMap;

public class Code01_TrieTree1 {
    public static class Node{
        private int end;
        private int pass;
        private Node[] nexts;

        Node() {
            end = 0;
            pass = 0;
            nexts = new Node[26];
        }

    }

    public static class TrieTree{
        private Node root;

        TrieTree() {
            root = new Node();
        }
        public void insert(String str) {
            if(str == null) {
                return;
            }
            char[] chars = str.toCharArray();
            Node node = root;
            node.pass++;
            for(int i=0;i<chars.length;i++) {
                int index = chars[i] - 'a';
                if(node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                    node = node.nexts[index];
                    node.pass++;
            }
            node.end++;

        }

        public int search(String str) {
            if(str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node node = root;
            for(int i=0;i<chars.length;i++) {
                int index = chars[i] - 'a';
                if(node.nexts[index] != null) {
                    node = node.nexts[index];
                }
                return 0;
            }
            return node.end;
        }

        public void delete(String str) {
            if(search(str) < 1) {
                return;
            }
            Node node = root;
            node.pass--;
            int index = 0;
            char[] chars = str.toCharArray();
            for(int i=0;i<chars.length;i++) {
                index = chars[i] - 'a';
                node = node.nexts[index];
                node.pass--;
                if(node.pass == 0) {
                    node = null;
                    break;
                }
            }
            node.end--;
        }

        public int prefixNumber(String str) {
            if(str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node node = root;
            for(int i=0;i<chars.length;i++) {
                int index = chars[i] - 'a';
                if(node.nexts[index] != null) {
                    node = node.nexts[index];
                }
                return 0;
            }
            return node.pass;
        }
    }

    public static class Node1 {
        private int pass;
        private int end;
        private HashMap<Integer,Node1> nexts;
        Node1() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class TrieTree1 {
        private Node1 root;
        TrieTree1() {
            root = new Node1();

        }

        public void insert(String str){
            char[] chars = str.toCharArray();
            int index = 0;
            Node1 node1 = root;
            node1.pass ++;
            for(int i=0;i<chars.length;i++) {
                index = (int)chars[i];
                if(! node1.nexts.containsKey(index)) {
                    node1.nexts.put(index,new Node1());
                }
                node1 = node1.nexts.get(index);
                node1.pass++;
            }
            node1.end++;
        }

        public int search(String str) {
            char[] chars = str.toCharArray();
            int index = 0;
            Node1 node1 = root;
            for(int i=0;i<chars.length;i++) {
                index = (int)chars[i];
                if(!node1.nexts.containsKey(index)) {
                    return 0;
                }
                node1 = node1.nexts.get(index);
            }
            return node1.end;
        }

        public int prefixNumber(String str) {
            char[] chars = str.toCharArray();
            int index = 0;
            Node1 node1 = root;
            for(int i=0;i<chars.length;i++) {
                index = (int)chars[i];
                if(!node1.nexts.containsKey(index)) {
                    return 0;
                }
                node1 = node1.nexts.get(index);
            }
            return node1.pass;
        }

        public void delete(String str) {
            if(search(str) < 1) {
                return;
            }
            char[] chars = str.toCharArray();
            int index = 0;
            Node1 node1 = root;
            node1.pass--;
            for(int i=0;i<chars.length;i++) {
                if(--node1.nexts.get(index).pass == 0) {
                    node1.nexts.remove(index);
                }
                node1 = node1.nexts.get(index);
            }

        }
    }

    public static class Right{
        private HashMap<String, Integer> box;
        Right() {
            box = new HashMap<>();
        }

        public void insert(String str) {
            if(!box.containsKey(str)) {
                box.put(str,1);
            } else {
                box.put(str,box.get(str) + 1);
            }
        }

        public int search(String str) {
            if(box.containsKey(str)) {
                return box.get(str);
            }
            return 0;
        }

        public int prefixNumber(String str) {
            int count = 0;
            for(String curr:box.keySet()) {
                if(curr.startsWith(str)){
                    count += box.get(curr);
                }
            }
            return count;
        }

        public void delete(String str) {
            if(box.containsKey(str)) {
                if(box.get(str) == 1) {
                    box.remove(str);
                }else {
                    box.put(str,box.get(str) -1);
                }

            }
        }
    }

    public static void main(String[] args) {
        int strlen = 10;
        int charlen = 100;
        int testTimes = 50000;
        for(int j=0;j<testTimes;j++) {
            String[] strings = generateStringArray(strlen,charlen);
            TrieTree trieTree = new TrieTree();
            TrieTree1 trieTree1 = new TrieTree1();
            Right right = new Right();
            for(int i=0;i<strings.length;i++) {
                double decide = Math.random();
                if(decide < 0.25) {
                    trieTree.insert(strings[i]);
                    trieTree1.insert(strings[i]);
                    right.insert(strings[i]);
                }else if(0.25 <= decide &&  decide < 0.5) {
                    int ans1 = trieTree.search(strings[i]);
                    int ans2 = trieTree1.search(strings[i]);
                    int ans3 = right.search(strings[i]);
                    if(ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                        return;
                    }
                }else if(0.5 <= decide &&  decide < 0.75) {
                    int ans1 = trieTree.prefixNumber(strings[i]);
                    int ans2 = trieTree1.prefixNumber(strings[i]);
                    int ans3 = right.prefixNumber(strings[i]);
                    if(ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                        return;
                    }
                }else {
                    trieTree.delete(strings[i]);
                    trieTree1.delete(strings[i]);
                    right.delete(strings[i]);
                }
            }
        }
        System.out.println("Nice!!");

    }

    private static String[] generateStringArray(int strlen, int charlen) {
        String[] strings = new String[strlen];
        for(int i=0;i<strlen;i++) {
            strings[i] = generateCharArray(charlen);
        }
        return strings;
    }

    private static String generateCharArray(int charlen) {
        char[] chars = new char[charlen];
        for(int i=0;i<charlen;i++) {
            int count = (int)(Math.random()*26);
            chars[i] = (char)(97 + count);
        }
        return String.valueOf(chars);
    }
}
