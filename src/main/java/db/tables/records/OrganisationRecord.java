/*
 * This file is generated by jOOQ.
 */
package db.tables.records;


import db.tables.Organisation;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.14.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrganisationRecord extends UpdatableRecordImpl<OrganisationRecord> implements Record4<Integer, String, Long, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.organisation.id</code>.
     */
    public OrganisationRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.organisation.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.organisation.name</code>.
     */
    public OrganisationRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.organisation.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.organisation.taxpayer_id</code>.
     */
    public OrganisationRecord setTaxpayerId(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.organisation.taxpayer_id</code>.
     */
    public Long getTaxpayerId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.organisation.checking_account</code>.
     */
    public OrganisationRecord setCheckingAccount(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.organisation.checking_account</code>.
     */
    public String getCheckingAccount() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Long, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, Long, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Organisation.ORGANISATION.ID;
    }

    @Override
    public Field<String> field2() {
        return Organisation.ORGANISATION.NAME;
    }

    @Override
    public Field<Long> field3() {
        return Organisation.ORGANISATION.TAXPAYER_ID;
    }

    @Override
    public Field<String> field4() {
        return Organisation.ORGANISATION.CHECKING_ACCOUNT;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Long component3() {
        return getTaxpayerId();
    }

    @Override
    public String component4() {
        return getCheckingAccount();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Long value3() {
        return getTaxpayerId();
    }

    @Override
    public String value4() {
        return getCheckingAccount();
    }

    @Override
    public OrganisationRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public OrganisationRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public OrganisationRecord value3(Long value) {
        setTaxpayerId(value);
        return this;
    }

    @Override
    public OrganisationRecord value4(String value) {
        setCheckingAccount(value);
        return this;
    }

    @Override
    public OrganisationRecord values(Integer value1, String value2, Long value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrganisationRecord
     */
    public OrganisationRecord() {
        super(Organisation.ORGANISATION);
    }

    /**
     * Create a detached, initialised OrganisationRecord
     */
    public OrganisationRecord(Integer id, String name, Long taxpayerId, String checkingAccount) {
        super(Organisation.ORGANISATION);

        setId(id);
        setName(name);
        setTaxpayerId(taxpayerId);
        setCheckingAccount(checkingAccount);
    }
}
