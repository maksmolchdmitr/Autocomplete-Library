package org.example.structure.trie.realization;

import org.example.structure.trie.Trie;

import java.util.*;

public class EconomicalTrie implements Trie {
    private static class Node {
        List<Character> characters;
        List<Node> nodes;
        boolean isWord = false;

        public Node getNode(char s) {
            if(characters==null) return null;
            for (int i = 0; i < characters.size(); i++) {
                if (s == characters.get(i)) {
                    return nodes.get(i);
                }
            }
            return null;
        }

        public void addNewNode(char s, Node node) {
            if (characters == null) characters = new ArrayList<>();
            if (nodes == null) nodes = new ArrayList<>();
            characters.add(s);
            nodes.add(node);
        }
    }

    private final Node root = new Node();

    @Override
    public void insert(String word) {
        Node curNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            char s = word.charAt(i);
            Node child = curNode.getNode(s);
            if (child == null) {
                child = new Node();
                curNode.addNewNode(s, child);
            }
            curNode = child;
        }
        curNode.isWord = true;
    }

    public List<String> searchByPrefix(String prefix) {
        Node curNode = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char s = prefix.charAt(i);
            Node child = curNode.getNode(s);
            if (child == null) return List.of();
            curNode = child;
        }
        List<String> res = getAllWordsFromNode(curNode, prefix);
        Collections.sort(res);
        return res;
    }

    private List<String> getAllWordsFromNode(Node node, String prefix) {
        List<String> words = new ArrayList<>();
        if (node.isWord) words.add(prefix);
        if (node.characters != null) {
            for (var s : node.characters) {
                words.addAll(getAllWordsFromNode(Objects.requireNonNull(node.getNode(s)), prefix + s));
            }
        }
        return words;
    }
}
