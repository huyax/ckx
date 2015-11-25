####基于spring
    目的是打造一个高可用，高性能，可扩展的基础Java开发平台
####拒绝重复造轮子
    对于单表节点的CRUD维护操作采用节点基础代码快速生成策略，界面、Action、业务及持久层均可快速生成
####管理界面
    简洁实用目前支持非传统的easyui风格后续正在开发bootstrap风格
####采用maven构建
    多模块依赖方式构建，层次清晰，代码规范，可横纵向扩展
####集成redis
    引入redis消息队列机制，支持shiro + redis集中式session管理机制
####引入lucene
    支持lucene + mysql方式的全文检索
