package org.example.structure.trie.realization;

import org.example.structure.trie.Trie;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public final class TriePublisher implements Trie {
    private static class Node {
        private Map<Character, Node> children;
        boolean isWord = false;

        private Node() {
            this.children = null;
        }

        public void insertChild(char symbol, Node node) {
            if (children == null) children = new TreeMap<>();
            children.put(symbol, node);
        }

        public Node getChild(char symbol) {
            if (this.children == null) return null;
            return this.children.get(symbol);
        }
    }

    private final Node root = new Node();

    @Override
    public void insert(String word) {
        Node curNode = createPathAndGetLastNodeByPrefix(word);
        curNode.isWord = true;
    }

    private Node createPathAndGetLastNodeByPrefix(String prefix) {
        Node curNode = this.root;
        Node child;
        for (char symbol : prefix.toCharArray()) {
            child = curNode.getChild(symbol);
            if (child == null) {
                child = new Node();
                curNode.insertChild(symbol, child);
            }
            curNode = child;
        }
        return curNode;
    }

    public void produceStringsByPrefix(String prefix, final Consumer<String> stringConsumer) {
        Node nodeByPrefix = findNodeByPrefix(prefix);
        if (nodeByPrefix == null) return;
        produceAllStringsFromNode(nodeByPrefix, stringConsumer, new StringBuilder(prefix));
    }

    private void produceAllStringsFromNode(Node curNode, final Consumer<String> stringConsumer, StringBuilder wordBuilder) {
        if (curNode.isWord) stringConsumer.accept(wordBuilder.toString());
        if (curNode.children != null) {
            for (var entry : curNode.children.entrySet()) {
                produceAllStringsFromNode(entry.getValue(), stringConsumer, wordBuilder.append(entry.getKey()));
                wordBuilder.deleteCharAt(wordBuilder.length() - 1);
            }
        }
    }

    private Node findNodeByPrefix(String prefix) {
        Node curNode = this.root;
        for (char symbol : prefix.toCharArray()) {
            curNode = curNode.getChild(symbol);
            if (curNode == null) return null;
        }
        return curNode;
    }
}
