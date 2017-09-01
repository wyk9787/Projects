# Block Chains

## Usage Example

`javac src/*.java`

`java src/BlockChainDriver 300` (You can replace 300 by any positive value to start with)

Since it's mining the nonce the nonce to match the criteria, it may take a few seconds to run before anything shows up

### Command

`mine` : discovers the nonce for a given transaction

`append` : appends a new block onto the end of the chain

`remove` : removes the last block from the end of the chain

`check` : checks that the block chain is valid

`report` : reports the balances of Alice and Bob

`help` : prints this list of commands

`quit` : quits the program


## Background
- Blockchain is a sequence of records built to be highly resitant to change
- Blockchains was first used in 2008 to record transactions for the cryptocurrency Bitcoin
- In this context, the blockchain is a complete history of all Bitcoin transactions ever made, a public ledger, replicated, verified, and evolved by many computers a distributed network of machines connected through the Internet.
- Blockchains have been used in many other contexts, e.g., other cryptocurrencies, crowd-funding and digital rights management, and supply chain management, anywhere where immutable public records of transactions are necessary.

## Description
- Develop a blockchain data structure to understand the essential operations that blockchain-based application perform.
