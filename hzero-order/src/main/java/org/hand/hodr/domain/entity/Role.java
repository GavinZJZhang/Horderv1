//package org.hand.hodr.domain.entity;
//
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import io.choerodon.core.oauth.CustomUserDetails;
//import io.choerodon.mybatis.annotation.ModifyAudit;
//import io.choerodon.mybatis.annotation.MultiLanguage;
//import io.choerodon.mybatis.annotation.MultiLanguageField;
//import io.choerodon.mybatis.annotation.VersionAudit;
//import io.choerodon.mybatis.domain.AuditDomain;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;
//import org.apache.commons.collections4.CollectionUtils;
//import org.hzero.core.base.BaseConstants.Flag;
//import org.hzero.core.message.MessageAccessor;
//import org.hzero.iam.domain.vo.RoleVO;
////import org.hzero.iam.infra.constant.HiamResourceLevel;
////import org.hzero.iam.infra.constant.LabelAssignType;
////import org.hzero.iam.infra.constant.Constants.RoleHierarchy;
//import org.hzero.mybatis.common.query.Where;
//import org.hzero.starter.keyencrypt.core.Encrypt;
//
//@ModifyAudit
//@VersionAudit
//@MultiLanguage
//@Table(
//        name = "iam_role"
//)
//public class Role extends AuditDomain {
//    public static final String ENCRYPT_KEY = "iam_role";
//    public static final String FIELD_ID = "id";
//    public static final String FIELD_CODE = "code";
//    public static final String FIELD_NAME = "name";
//    public static final String FIELD_LEVEL = "level";
//    public static final String FIELD_DESCRIPTION = "description";
//    public static final String FIELD_LEVEL_PATH = "levelPath";
//    public static final String FIELD_INHERIT_LEVEL_PATH = "inheritLevelPath";
//    public static final String FIELD_IS_ENABLED = "isEnabled";
//    public static final String FIELD_ROLE_ID = "roleId";
//    public static final String FIELD_TENANT_ID = "tenantId";
//    public static final String FIELD_PARENT_ROLE_ID = "parentRoleId";
//    public static final String FIELD_INHERIT_ROLE_ID = "inheritRoleId";
//    public static final String FIELD_PARENT_ROLE_ASSIGN_LEVEL = "parentRoleAssignLevel";
//    public static final String FIELD_PARENT_ROLE_ASSIGN_LEVEL_VALUE = "parentRoleAssignLevelValue";
//    public static final String FIELD_BUILD_IN = "isBuiltIn";
//    public static final String FIELD_CREATED_BY_TENANT_ID = "createdByTenantId";
//    public static final String FIELD_TPL_ROLE_NAME = "tpl_role_name";
//    public static final String FIELD_ROLE_HIERARCHY = "roleHierarchy";
//    public static final String FIELD_ENABLE_ROLE_PERMISSION = "enableRolePermission";
//    public static final Long ROOT_ID = 0L;
//    public static final String LEVEL_PATH_SEPARATOR = "|";
//    public static final String LABEL_DATA_TYPE = "ROLE";
//    public static final String LABEL_TENANT_ADMIN = "TENANT_ADMIN";
//    public static final String LABEL_TENANT_ROLE_TPL = "TENANT_ROLE_TPL";
//    public static final String LABEL_ROLE_TPL = "_ROLE_TPL";
//    public static final String LABEL_ROLE_TPL_LIKE = "%_ROLE_TPL";
//    public static final String SUPER_SITE_ROLE = "role/site/default/administrator";
//    public static final String SUPER_TENANT_ROLE = "role/organization/default/administrator";
//    public static final String TENANT_ROLE_TPL = "role/organization/default/template/administrator";
//    @Id
//    @Where
//    @GeneratedValue
//    @Encrypt
//    private Long id;
//    @MultiLanguageField
//    @NotEmpty
//    private String name;
//    @NotEmpty
//    @Pattern(
//            regexp = "^[a-z0-9][a-z0-9-_./]*$"
//    )
//    @Where
//    private String code;
//    private String description;
//    @Column(
//            name = "fd_level"
//    )
//    private String level;
//    private Boolean isEnabled;
//    private Boolean isModified;
//    private Boolean isEnableForbidden;
//    private Boolean isBuiltIn;
//    private Boolean isAssignable;
//    private Long objectVersionNumber;
//    @Column(
//            name = "h_tenant_id"
//    )
//    @MultiLanguageField
//    private Long tenantId;
//    @Column(
//            name = "h_inherit_role_id"
//    )
//    @Where
//    @Encrypt
//    private Long inheritRoleId;
//    @Column(
//            name = "h_parent_role_id"
//    )
//    @Where
//    @Encrypt
//    private Long parentRoleId;
//    @Column(
//            name = "h_parent_role_assign_level"
//    )
//    private String parentRoleAssignLevel;
//    @Column(
//            name = "h_parent_role_assign_level_val"
//    )
//    private Long parentRoleAssignLevelValue;
//    @Column(
//            name = "h_level_path"
//    )
//    @Where
//    private String levelPath;
//    @Column(
//            name = "h_inherit_level_path"
//    )
//    private String inheritLevelPath;
//    private Long createdByTenantId;
//    @MultiLanguageField
//    private String tplRoleName;
//    private Integer roleHierarchy;
//    private Boolean enableRolePermission;
//    @Transient
//    private List<RolePermission> permissionSets = new ArrayList();
//    @Transient
//    @Encrypt
//    private Long copyFromRoleId;
//    @Transient
//    private String tenantNum;
//    @Transient
//    private String createdByTenantNum;
//    @Transient
//    @JsonIgnore
//    private Role parentRole;
//    @Transient
//    @JsonIgnore
//    private List<Role> createdSubRoles;
//    @Transient
//    @JsonIgnore
//    private Role inheritRole;
//    @Transient
//    @JsonIgnore
//    private Role copyRole;
//    @Transient
//    @JsonIgnore
//    private List<Role> inheritSubRoles;
//    @Transient
//    private List<Label> roleLabels;
//    @Transient
//    @JsonIgnore
//    private LabelAssignType labelAssignType;
//    @Transient
//    private Integer tplRoleFlag;
//
//    public Role() {
//    }
//
////    public static void setupRoleControlFlag(List<RoleVO> roles, List<Long> allManageRoleIds, List<MemberRole> cantRemoveRoles, Long memberId, CustomUserDetails self) {
////        Set<Long> cantRemoveRoleIds = (Set)cantRemoveRoles.stream().filter((item) -> {
////            return memberId.equals(item.getMemberId());
////        }).map(MemberRole::getRoleId).collect(Collectors.toSet());
////        boolean isCurrentMemberUser = self.getUserId().equals(memberId);
////        Iterator var7 = roles.iterator();
////
////        while(var7.hasNext()) {
////            RoleVO role = (RoleVO)var7.next();
////            if (RoleHierarchy.MULTI.equals(role.getRoleHierarchy())) {
////                if (!allManageRoleIds.contains(role.getId())) {
////                    role.setManageableFlag(Flag.NO);
////                    role.setRemovableFlag(Flag.NO);
////                    role.setTipMessage(MessageAccessor.getMessage("hiam.info.role.denyOperateNoManageableRole").desc());
////                }
////
////                if (cantRemoveRoleIds.contains(role.getId())) {
////                    role.setRemovableFlag(Flag.NO);
////                    if (isCurrentMemberUser) {
////                        role.setTipMessage(MessageAccessor.getMessage("hiam.info.role.denyOperateSelfTopRole").desc());
////                    } else {
////                        role.setTipMessage(MessageAccessor.getMessage("hiam.info.role.denyOperateNoManageableRole").desc());
////                    }
////                }
////            } else if (!allManageRoleIds.contains(role.getId())) {
////                role.setManageableFlag(Flag.NO);
////                role.setRemovableFlag(Flag.NO);
////                role.setTipMessage(MessageAccessor.getMessage("hiam.info.role.denyOperateNoManageableRole").desc());
////            }
////        }
//
//    }
//
//    public static Set<Long> filterTopRoleIds(List<RoleVO> roles) {
//        return (Set)filterTopRoles(roles).stream().map(RoleVO::getId).collect(Collectors.toSet());
//    }
//
//    public static List<RoleVO> filterTopRoles(List<RoleVO> roles) {
//        if (CollectionUtils.isEmpty(roles)) {
//            return Collections.emptyList();
//        } else {
//            Set<String> levelPaths = (Set)roles.stream().map(RoleVO::getLevelPath).collect(Collectors.toSet());
//            List<RoleVO> topRole = new ArrayList(roles.size());
//            Iterator var3 = roles.iterator();
//
//            while(var3.hasNext()) {
//                RoleVO role = (RoleVO)var3.next();
//                String levelPath = role.getLevelPath();
//                boolean isTop = levelPaths.stream().filter((path) -> {
//                    return !levelPath.equals(path);
//                }).noneMatch((path) -> {
//                    return levelPath.startsWith(path + "|");
//                });
//                if (isTop) {
//                    topRole.add(role);
//                }
//            }
//
//            return topRole;
//        }
//    }
//
//    public void setupParentAssignLevel(Role parentRole) {
//        this.parentRoleAssignLevel = HiamResourceLevel.ORGANIZATION.value();
//        if (parentRole != null) {
//            this.parentRoleAssignLevelValue = parentRole.getTenantId();
//        } else {
//            this.parentRoleAssignLevelValue = this.tenantId;
//        }
//
//    }
//
//    public void buildCreatedRoleLevelPath(Role parentRole) {
//        if (Boolean.TRUE.equals(this.isBuiltIn) && parentRole == null) {
//            this.levelPath = this.code;
//        } else {
//            String path = this.generatePath();
//            if (parentRole == null) {
//                this.levelPath = path;
//            } else {
//                this.levelPath = String.format("%s%s%s", parentRole.getLevelPath(), "|", path);
//            }
//
//        }
//    }
//
//    public void buildInheritRoleLevelPath(Role inheritRole) {
//        if (Boolean.TRUE.equals(this.isBuiltIn)) {
//            this.inheritLevelPath = this.code;
//        } else {
//            String path = this.generatePath();
//            if (inheritRole != null) {
//                this.inheritLevelPath = String.format("%s%s%s", inheritRole.getInheritLevelPath(), "|", path);
//            } else {
//                this.inheritLevelPath = path;
//            }
//
//        }
//    }
//
//    private String generatePath() {
//        String createdByFlag = this.getCreatedByTenantId() == null ? "null" : (String)Optional.ofNullable(this.getCreatedByTenantNum()).orElse(this.getCreatedByTenantId().toString());
//        return String.format("%s.%s.%s", this.getTenantNum(), createdByFlag, this.getCode());
//    }
//
//    public void initSupperRoleLevelPath() {
//        this.levelPath = this.code;
//        this.inheritLevelPath = this.code;
//    }
//
//    public List<Label> getRoleLabels() {
//        return this.roleLabels;
//    }
//
//    public void setRoleLabels(List<Label> roleLabels) {
//        this.roleLabels = roleLabels;
//    }
//
//    public LabelAssignType getLabelAssignType() {
//        return this.labelAssignType;
//    }
//
//    public void setLabelAssignType(LabelAssignType labelAssignType) {
//        this.labelAssignType = labelAssignType;
//    }
//
//    public Long getId() {
//        return this.id;
//    }
//
//    public Role setId(Long id) {
//        this.id = id;
//        return this;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCode() {
//        return this.code;
//    }
//
//    public Role setCode(String code) {
//        this.code = code;
//        return this;
//    }
//
//    public String getDescription() {
//        return this.description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getLevel() {
//        return this.level;
//    }
//
//    public void setLevel(String level) {
//        this.level = level;
//    }
//
//    public Boolean getEnabled() {
//        return this.isEnabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.isEnabled = enabled;
//    }
//
//    public Boolean getModified() {
//        return this.isModified;
//    }
//
//    public void setModified(Boolean modified) {
//        this.isModified = modified;
//    }
//
//    public Boolean getEnableForbidden() {
//        return this.isEnableForbidden;
//    }
//
//    public void setEnableForbidden(Boolean enableForbidden) {
//        this.isEnableForbidden = enableForbidden;
//    }
//
//    public Boolean getBuiltIn() {
//        return this.isBuiltIn;
//    }
//
//    public void setBuiltIn(Boolean builtIn) {
//        this.isBuiltIn = builtIn;
//    }
//
//    public Boolean getAssignable() {
//        return this.isAssignable;
//    }
//
//    public void setAssignable(Boolean assignable) {
//        this.isAssignable = assignable;
//    }
//
//    public Long getObjectVersionNumber() {
//        return this.objectVersionNumber;
//    }
//
//    public void setObjectVersionNumber(Long objectVersionNumber) {
//        this.objectVersionNumber = objectVersionNumber;
//    }
//
//    public Long getTenantId() {
//        return this.tenantId;
//    }
//
//    public Role setTenantId(Long tenantId) {
//        this.tenantId = tenantId;
//        return this;
//    }
//
//    public Long getInheritRoleId() {
//        return this.inheritRoleId;
//    }
//
//    public void setInheritRoleId(Long inheritRoleId) {
//        this.inheritRoleId = inheritRoleId;
//    }
//
//    public Long getParentRoleId() {
//        return this.parentRoleId;
//    }
//
//    public void setParentRoleId(Long parentRoleId) {
//        this.parentRoleId = parentRoleId;
//    }
//
//    public String getParentRoleAssignLevel() {
//        return this.parentRoleAssignLevel;
//    }
//
//    public void setParentRoleAssignLevel(String parentRoleAssignLevel) {
//        this.parentRoleAssignLevel = parentRoleAssignLevel;
//    }
//
//    public Long getParentRoleAssignLevelValue() {
//        return this.parentRoleAssignLevelValue;
//    }
//
//    public void setParentRoleAssignLevelValue(Long parentRoleAssignLevelValue) {
//        this.parentRoleAssignLevelValue = parentRoleAssignLevelValue;
//    }
//
//    public List<RolePermission> getPermissionSets() {
//        return this.permissionSets;
//    }
//
//    public void setPermissionSets(List<RolePermission> permissionSets) {
//        this.permissionSets = permissionSets;
//    }
//
//    public Long getCopyFromRoleId() {
//        return this.copyFromRoleId;
//    }
//
//    public void setCopyFromRoleId(Long copyFromRoleId) {
//        this.copyFromRoleId = copyFromRoleId;
//    }
//
//    public String getLevelPath() {
//        return this.levelPath;
//    }
//
//    public Role setLevelPath(String levelPath) {
//        this.levelPath = levelPath;
//        return this;
//    }
//
//    public String getInheritLevelPath() {
//        return this.inheritLevelPath;
//    }
//
//    public void setInheritLevelPath(String inheritLevelPath) {
//        this.inheritLevelPath = inheritLevelPath;
//    }
//
//    public Long getCreatedByTenantId() {
//        return this.createdByTenantId;
//    }
//
//    public void setCreatedByTenantId(Long createdByTenantId) {
//        this.createdByTenantId = createdByTenantId;
//    }
//
//    public String getTplRoleName() {
//        return this.tplRoleName;
//    }
//
//    public void setTplRoleName(String tplRoleName) {
//        this.tplRoleName = tplRoleName;
//    }
//
//    public Integer getRoleHierarchy() {
//        return this.roleHierarchy;
//    }
//
//    public void setRoleHierarchy(Integer roleHierarchy) {
//        this.roleHierarchy = roleHierarchy;
//    }
//
//    public Boolean getEnableRolePermission() {
//        return this.enableRolePermission;
//    }
//
//    public void setEnableRolePermission(Boolean enableRolePermission) {
//        this.enableRolePermission = enableRolePermission;
//    }
//
//    public Role getParentRole() {
//        return this.parentRole;
//    }
//
//    public void setParentRole(Role parentRole) {
//        this.parentRole = parentRole;
//    }
//
//    public List<Role> getCreatedSubRoles() {
//        return this.createdSubRoles;
//    }
//
//    public void setCreatedSubRoles(List<Role> createdSubRoles) {
//        this.createdSubRoles = createdSubRoles;
//    }
//
//    public Role getInheritRole() {
//        return this.inheritRole;
//    }
//
//    public void setInheritRole(Role inheritRole) {
//        this.inheritRole = inheritRole;
//    }
//
//    public Role getCopyRole() {
//        return this.copyRole;
//    }
//
//    public void setCopyRole(Role copyRole) {
//        this.copyRole = copyRole;
//    }
//
//    public List<Role> getInheritSubRoles() {
//        return this.inheritSubRoles;
//    }
//
//    public void setInheritSubRoles(List<Role> inheritSubRoles) {
//        this.inheritSubRoles = inheritSubRoles;
//    }
//
//    public String getTenantNum() {
//        return this.tenantNum;
//    }
//
//    public void setTenantNum(String tenantNum) {
//        this.tenantNum = tenantNum;
//    }
//
//    public String getCreatedByTenantNum() {
//        return this.createdByTenantNum;
//    }
//
//    public void setCreatedByTenantNum(String createdByTenantNum) {
//        this.createdByTenantNum = createdByTenantNum;
//    }
//
//    public Integer getTplRoleFlag() {
//        return this.tplRoleFlag;
//    }
//
//    public void setTplRoleFlag(Integer tplRoleFlag) {
//        this.tplRoleFlag = tplRoleFlag;
//    }
//
//    public String toString() {
//        return "Role{id=" + this.id + ", name='" + this.name + '\'' + ", code='" + this.code + '\'' + ", description='" + this.description + '\'' + ", level='" + this.level + '\'' + ", isEnabled=" + this.isEnabled + ", isModified=" + this.isModified + ", isEnableForbidden=" + this.isEnableForbidden + ", isBuiltIn=" + this.isBuiltIn + ", isAssignable=" + this.isAssignable + ", objectVersionNumber=" + this.objectVersionNumber + ", tenantId=" + this.tenantId + ", inheritRoleId=" + this.inheritRoleId + ", parentRoleId=" + this.parentRoleId + ", parentRoleAssignLevel='" + this.parentRoleAssignLevel + '\'' + ", parentRoleAssignLevelValue=" + this.parentRoleAssignLevelValue + ", levelPath='" + this.levelPath + '\'' + ", inheritLevelPath='" + this.inheritLevelPath + '\'' + ", copyFromRoleId=" + this.copyFromRoleId + ", roleLabels=" + this.roleLabels + ", tplRoleFlag=" + this.tplRoleFlag + '}';
//    }
//}
//
