# Wallet Technology Overview
控制对用户资金的访问、管理密钥和地址、跟踪余额以及创建和签署交易. 此外，一些以太坊钱包还可以与合约进行交互，例如 ERC20 代币;   
存储和管理用户密钥的系统; 每个钱包都有一个密钥管理组件

# wallet分类
* Nondeterministic (Random) Wallets  
* Deterministic (Seeded) Wallets
  
确定性或“种子”钱包是包含私钥的钱包，这些私钥均源自单个主密钥或种子。  
种子是一个随机生成的数字，它与其他数据 （例如索引号或“链码”（请参阅扩展的公钥和私钥））组合，以派生任意数量的私钥。  
在确定性钱包中，种子足以恢复所有派生密钥，因此在创建时的单个备份足以保护钱包中的所有资金和智能合约。  
种子也足以用于钱包导出或导入，允许在不同钱包实现之间轻松迁移所有密钥。最先进的确定性钱包形式是比特币BIP-32 标准定义的分层确定性（HD）钱包。
HD 钱包包含以树结构派生的密钥
# Hierarchical Deterministic Wallets (BIP-32/BIP-44)
# Seeds and Mnemonic Codes (BIP-39)
# bip-44
# Wallet Best Practices
使用助记词（BIP39）   
使用层级确定性钱包（HD Wallets）（BIP32）   
使用多目的HD Wallets（BIP43）   
使用多币种，多账号的HD Wallets (BIP44)
## Mnemonic Code Words (BIP-39)
### 使用助记码
#### python-mnemonic
The reference implementation of the standard by the SatoshiLabs team that proposed BIP-39, in Python
#### ConsenSys/eth-lightwallet
Lightweight JS Ethereum wallet for nodes and browser (with BIP-39)
#### npm/bip39
JavaScript implementation of Bitcoin BIP-39: Mnemonic code for generating deterministic keys
#### BIP-39 生成器（作为独立网页的 BIP-39 生成器）
### web3j
## Creating an HD Wallet from the Seed
## HD Wallets (BIP-32) and Paths (BIP-43/44)
# kind
* 多签钱包  
ex: Gnosis Safe  
建立在智能合约上的钱包，合约中定义了验证逻辑，比如如果需要验证一笔交易，需要一个以上的私钥，或者五个中至少三个私钥进行验证
* MPC(Multi-Party Computation)Wallet   
对私钥进行多方计算在链下实现多签、跨链等更复杂的验证   
将一个私钥打碎成多片