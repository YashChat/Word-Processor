import java.util.LinkedList;
import java.util.List;

/**
 * A Simple Word Processing class
 *
 * <p>Purdue University -- CS25100 -- Fall 2019 -- Tries</p>
 *
 */
public class WordProcessor {

    private Node wordTrie;  //Root Node of the Trie

    /**
     * A simple Node class representing each
     * individual node of the trie
     */
    public class Node {

        protected char c;
        protected Node left, equal, right;
        protected boolean isEnd = false;

        /**
         * Constructor for Node class
         * @param ca Character assigned to the node
         */
        public Node(char ca) {
            this.c = ca;
            this.equal = null;
            this.right = null;
            this.left = null;
            this.isEnd = false;
        }
    }

    /**
     * Defualt constructor for the WordProcessor class
     */
    public WordProcessor() {

        wordTrie = null;
    }

    /**
     * Method to add a string to the trie
     * @param s String to be added
     */
    public void addWord(String s) {
        if (wordTrie == null) {
            wordTrie = new Node(s.charAt(0));
        }

        wordTrie = addword_helper(wordTrie, s, 0);
    } // addWord()

    public Node addword_helper(Node added, String word, int position) {
        char[] string_array = word.toCharArray();
        if (string_array[position] > added.c) {
            added.right = addword_helper(added.right, word, position)
        }
        else if (string_array[position] < added.c) {
            added.leftt = addword_helper(added.left, word, position)
        }
        else if ((position + 1) < word.length()) {
            added.equal = addword_helper(added.equal, word, (position + 1));
        }
        else {
            added.isEnd = true;
        }
        return added;
    } // addword_helper



    /**
     * Method to add an array of strings to the trie
     * @param s Array of strings to be added
     */
    public void addAllWords(String[] s) {
        for (int i = 0; i < s.length; i++) {
            String word = s[i];
            addWord(word);
        }
    }

    /**
     * Method to check of a string exists in the trie
     * @param s String to be checked
     * @return true if string exists
     */
    public boolean wordSearch(String s) {
        if (wordTrie == null) {
            return false;
        }
        return wordSearch_helper;
    }

    public boolean wordSearch_helper(Node searched, String word, int position) {
        char[] string_array = word.toCharArray();
        if (string_array[position] > searched.c) {
            return wordSearch_helper(searched.right, word, position)
        }
        else if (string_array[position] < searched.c) {
            return wordSearch_helper(searched.left, word, position)
        }
        else if (position == (word.length() - 1)) {
            if (searched.isEnd != true) {
                return false;
            }
            return true;
        }
        else {
            return wordSearch_helper(searched.equal, word, ([position + 1]));
        }
    }


    /**
     * Method to check if the trie if empty
     * (No stirngs added yet)
     * @return
     */
    public boolean isEmpty()
    {
        if (wordTrie == null) {
            return true;
        }
        return false;
    }

    /**
     * Method to empty the trie
     */
    public void clear()
    {
        wordTrie = null;
    }


    /**
     * Getter for wordTire
     * @return Node wordTrie
     */
    public Node getWordTrie() {
        return wordTrie;
    }


    /**
     * Method to search autocomplete options
     * @param s Prefix string being searched for
     * @return List of strings representing autocomplete options
     */
    public List<String> autoCompleteOptions(String s) {
        //System.out.println("String to autocomplete: " + s);
        if (isEmpty() == true) {
            return null;
        }

        Node temp = wordTrie;
        LinkedList<String> options_strings = new LinkedList<String>();
        char[] string_array = s.toCharArray();
        if (wordSearch(s) == true) {
            return options_strings;
        }

        int counter = 0;
        while ((temp != null) && (counter < letters.length)) {
            if (temp.c > letters[counter]) {
                if (temp != null) {
                    temp = temp.left;
                }
            }
            if (temp.c < letters[counter]) {
                temp = temp.right;
            }
            if (temp.c == letters[counter]) {
                if (temp != null) {
                    temp = temp.equal;
                    counter++;
                }
            }
        }

        string_array = travel(temp, string_array, s);
        return string_array;
    }

    public LinkedList<String> travel(Node current_node, LinkedList<String> letters, String prefix) {
        if (current_node != null) {
            travel(current_node.left, letters, prefix);

            if (current_node.isEnd) {
                letters.add(prefix + current_node.c);
            }
            travel(current_node.equal, (prefix + current_node.c), letters);
            travel(current_node.right, prefix, letters);
        }
    }
    public Node getPrefix(Node current_node, char[] letters) {
        Node temp = current_node;
        int counter = 0;
        while ((temp != null) && (counter < letters.length)) {
            if (temp.c > letters[counter]) {
                if (temp != null) {
                    temp = temp.left;
                }
            }
            if (temp.c < letters[counter]) {
                temp = temp.right;
            }
            if (temp.c == letters[counter]) {
                if (temp != null) {
                    temp = temp.equal;
                    counter++;
                }
            }
        }
        return temp;
    }
}
