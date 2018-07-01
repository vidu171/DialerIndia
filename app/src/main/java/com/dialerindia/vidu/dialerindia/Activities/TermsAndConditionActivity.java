package com.dialerindia.vidu.dialerindia.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.TextView;

import com.dialerindia.vidu.dialerindia.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermsAndConditionActivity extends BaseActivity {
    @BindView(R.id.terms_and_condition_textview)
    TextView TermsAndCondition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_condition);
        ButterKnife.bind(this);
        String summary = "\n" +
                "1\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "OPTUS GENERAL TERMS\n" +
                "Capitalised words in a clause of these General Terms have the meaning given in that clause or\n" +
                "in the dictionary at the end of these General Terms. These General Terms are interpreted\n" +
                "according to the rules of interpretation in the dictionary.\n" +
                "1 AGREEMENT TO SUPPLY THE SERVICE\n" +
                "1.1 Optus will supply the Service on the terms and conditions of the standard form of\n" +
                "agreement for the Service which comprises:\n" +
                "(a) the Standard Pricing Table;\n" +
                "(b) the Service Description; and\n" +
                "(c) these General Terms.\n" +
                "If any of the terms of those documents is inconsistent with any other of those terms,\n" +
                "then the documents and their terms will prevail in the order set out above, except that\n" +
                "clauses 16 (Your Rights Against Optus) and 17 (End Users etc) always prevail over all\n" +
                "other terms in any of those documents.\n" +
                "1.2 You may request Optus to supply the Service to you in a manner accepted by Optus\n" +
                "from time to time which may include using the relevant Optus approved standard\n" +
                "application form from time to time (“Application”). Your Application specifies the\n" +
                "Service you have selected and may specify options and parameters you have selected\n" +
                "for that Service.\n" +
                "1.3 If you acquire more than one service from Optus, then each service will be provided\n" +
                "under the terms and conditions of the standard form of agreement for that service.\n" +
                "2 YOUR USE OF THE SERVICE\n" +
                "2.1 Optus must supply the Service from the Service Start Date.\n" +
                "2.2 You must reasonably co-operate with Optus to allow Optus to establish and supply the\n" +
                "Service to you safely and efficiently. This includes following Optus’ reasonable\n" +
                "requests to provide:\n" +
                "(a) any necessary forecasts to Optus, on which Optus will rely in establishing\n" +
                "and supplying the Service to you; and\n" +
                "(b) Optus’ Personnel with safe and prompt access to the Premises and your\n" +
                "Personnel, equipment, data and information.\n" +
                "2.3 In using the Service, you must comply with all laws, all directions by a Regulator and\n" +
                "reasonable directions by Optus.\n" +
                "2.4 You must not use, or attempt to use, the Service:\n" +
                "\n" +
                "2\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "(a) to break any law or to infringe another person’s rights;\n" +
                "(b) to expose Optus to liability;\n" +
                "(c) in any way which damages, interferes with or Interrupts the Service, or any\n" +
                "telecommunications network, equipment, or facilities, or cabling controlled by\n" +
                "Optus (“Optus Network”) or a Supplier (“Supplier Network”) to supply the\n" +
                "Service, as those things are configured at the time;\n" +
                "(d) in excess of any forecast provided to Optus under clause 2.2(a), except where\n" +
                "you are expressly permitted to do so in the Service Description; or\n" +
                "(e) in any way which may damage any property or injure or kill any person.\n" +
                "2.5 You:\n" +
                "(a) must not use, or attempt to use, the Service to transmit, publish or\n" +
                "communicate material which is defamatory, offensive, abusive, indecent,\n" +
                "menacing or unwanted; and\n" +
                "(b) acknowledge that, where the Service is a Carriage Service, Optus may be\n" +
                "required to intercept communications over the Service and may also monitor\n" +
                "your usage of the Service and communications sent over it.\n" +
                "2.6 Optus may ask you to stop doing something which Optus reasonably believes is\n" +
                "contrary to clauses 2.4 or 2.5. You must immediately comply with any such request. If\n" +
                "you do not, then Optus may take any steps reasonably necessary to ensure compliance\n" +
                "with clauses 2.4 or 2.5 or the request.\n" +
                "2.7 To the extent permitted by law, you acknowledge that Optus makes no warranty in\n" +
                "relation to the performance or characteristics of any software supplied in connection\n" +
                "with the Service.\n" +
                "3 EQUIPMENT\n" +
                "3.1 Optus or its Personnel may provide or lease to you, in connection with the Service,\n" +
                "equipment or cabling which it, or they, own or lease (“Optus Owned Equipment”).\n" +
                "3.2 Title in the Optus Owned Equipment is not transferred to you. You hold the Optus\n" +
                "Owned Equipment as bailee for Optus or the relevant member of its Personnel. Risk in\n" +
                "any Optus Owned Equipment passes to you on delivery. You are responsible for any\n" +
                "damage to, or destruction or theft of, the Optus Owned Equipment, except to the\n" +
                "extent it is caused by Optus. You must keep the Optus Owned Equipment free from\n" +
                "any charge, lien, mortgage or encumbrance.\n" +
                "3.3 Unless otherwise agreed, you must:\n" +
                "(a) allow Optus' Personnel, and only Optus' Personnel, to service, modify, repair or\n" +
                "replace the Optus Owned Equipment; and\n" +
                "\n" +
                "3\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "(b) do all things reasonably required by Optus to make clear the identity of the\n" +
                "owner of the Optus Owned Equipment.\n" +
                "3.4 You may purchase equipment from Optus or any of its Personnel for use in connection\n" +
                "with the Service. Title to that equipment passes to you when you pay for it in full in\n" +
                "cleared funds. Risk in that equipment passes to you on delivery. Optus will use\n" +
                "reasonable efforts to transfer to you any manufacturer's warranty in any such\n" +
                "equipment, from the time title passes to you.\n" +
                "3.5 You must ensure that all equipment you use in connection with the Service, and the\n" +
                "way you use that equipment, complies with all laws, as well as directions by a\n" +
                "Regulator and reasonable directions by Optus. If you do not do so, Optus may\n" +
                "disconnect that equipment from the Service. Optus will try to give you reasonable\n" +
                "notice before disconnection, but may do so immediately in an emergency.\n" +
                "3.6 You must provide adequate and suitable space, power supply and environment for all\n" +
                "equipment used in connection with the Service and located on the Premises.\n" +
                "4 MAINTENANCE\n" +
                "4.1 Optus may conduct maintenance on the Optus Network. Optus will try to conduct\n" +
                "scheduled maintenance outside normal business hours (Sydney time) but may not\n" +
                "always be able to do so.\n" +
                "4.2 Except to the extent the Service Description expressly provides otherwise, Optus is not\n" +
                "responsible for rectifying any fault in the Service where the fault arises in or is caused\n" +
                "by a Supplier Network, your equipment or other equipment or facilities beyond the\n" +
                "boundary of the Optus Network (as defined in the Telecommunications Act).\n" +
                "5 SERVICE CHARGES\n" +
                "5.1 You must pay the charges for the Service set out in the Standard Pricing Table (or the\n" +
                "terms of an applicable Special under clauses 5.3 and 5.4) and any additional charges set\n" +
                "out in the Application and in accordance with any applicable provisions of the Service\n" +
                "Description. Charges for the Service accrue from no later than the Service Start Date.\n" +
                "5.2 Optus may charge you an additional amount:\n" +
                "(a) to service, modify, repair or replace the Service or any equipment, as a result\n" +
                "of an Excluded Event; and\n" +
                "(b) for installation, if the information you provided to Optus to determine the\n" +
                "installation required was incomplete or inaccurate; and\n" +
                "(c) for installation, maintenance or other services which you request Optus to\n" +
                "provide outside normal business hours (Sydney time).\n" +
                "5.3 From time to time Optus may offer special promotions or offers (“Specials”) in\n" +
                "connection with the Service. A Special may apply to or involve more than one service,\n" +
                "and it may be notified in a general advertisement or specifically provided to you. A\n" +
                "Special may be subject to certain conditions, including where Optus may:\n" +
                "\n" +
                "4\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "(a) offer to vary the price;\n" +
                "(b) offer to vary the terms of supply (including the Committed Term); or\n" +
                "(c) require you to acquire all relevant services for a minimum period and to pay a\n" +
                "fee if you cancel supply prior to the expiry of that minimum period.\n" +
                "5.4 You may accept the Special by any means notified by Optus (which may include\n" +
                "completing and signing an Application that includes some or all of the terms of the\n" +
                "Special). If you validly accept a Special, the terms of that Special will prevail to the\n" +
                "extent of any inconsistency over those which would otherwise apply in the Agreement,\n" +
                "and will apply for at least the duration of the Special notified by Optus. In all other\n" +
                "respects, the terms and conditions of the Agreement continue to apply. After the\n" +
                "Special expires, Optus may end the Special pricing and terms.\n" +
                "5.5 In calculating charges, Optus need only look at billing information generated or\n" +
                "received by Optus or its Suppliers. Optus may round up any charge to the nearest\n" +
                "cent.\n" +
                "6 INVOICES AND PAYMENT\n" +
                "6.1 Unless otherwise agreed, Optus may invoice you:\n" +
                "(a) for any equipment you purchase from Optus or its Personnel, on or after\n" +
                "delivery;\n" +
                "(b) for any installation charges, after installation;\n" +
                "(c) for variable charges, in arrears;\n" +
                "(d) for recurring or fixed charges, in advance; and\n" +
                "(e) otherwise, as notified by Optus from time to time.\n" +
                "6.2 Optus will notify you of its billing period from time to time. Optus may also issue\n" +
                "interim invoices for accrued charges.\n" +
                "6.3 Optus may invoice you using a billing agent (which may be another Optus Group\n" +
                "Company).\n" +
                "6.4 Subject to clause 7 (Billing Disputes), you will pay each amount invoiced in the way\n" +
                "and by the due date specified in the invoice or which Optus notifies you from time to\n" +
                "time.\n" +
                "6.5 Optus will try to include on your invoice all charges for the relevant billing period.\n" +
                "This is not always possible; for example, because Optus receives an invoice for your\n" +
                "Service from another Supplier, or because of an error or problem with the relevant\n" +
                "Optus billing system. Optus may include these unbilled charges in any later invoice(s).\n" +
                "6.6 Optus may re-issue any invoice if any error is later discovered. If you have overpaid as\n" +
                "a result of a billing error, your account will be credited with the overpayment or, if you\n" +
                "\n" +
                "5\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "have stopped acquiring the Service from Optus, Optus will refund the overpayment\n" +
                "promptly after your request and after deduction of any other amounts due by you to\n" +
                "Optus or an Optus Group Company.\n" +
                "6.7 You must pay all charges incurred in respect of the Service and equipment whether or\n" +
                "not the charges and the related use of the Service were authorised by you. This\n" +
                "includes charges incurred in connection with your End Users' use of the Service.\n" +
                "6.8 If you do not pay any amount invoiced by the due date (except any amount which is\n" +
                "validly disputed under clause 7), then Optus may charge you a late fee of 2% per\n" +
                "annum above Optus' corporate overdraft rate from time to time calculated on the daily\n" +
                "balance of the unpaid amount from the due date until the date of payment in full. This\n" +
                "is an independent obligation which applies before and after judgment. You must also\n" +
                "pay Optus’ expenses in recovering payment from you.\n" +
                "6.9 If you choose to pay by direct debit and your financial institution fails to honour your\n" +
                "payment, you must pay the amount owing directly to Optus and an additional\n" +
                "administration fee may apply.\n" +
                "6.10 Subject to clause 8 (Taxes), you may not withhold, deduct or set-off any amount from\n" +
                "or against any payment due by you to Optus in any circumstances. Optus may withhold\n" +
                "or deduct from or set-off against any amount which Optus or an Optus Group\n" +
                "Company must otherwise pay or credit to you any amount payable by you to Optus or\n" +
                "an Optus Group Company.\n" +
                "7 BILLING DISPUTES\n" +
                "7.1 You may dispute an amount invoiced by Optus but only if you do so in accordance\n" +
                "with this clause 7.\n" +
                "7.2 Except to the extent you raise a valid billing dispute in respect of an Optus invoice, you\n" +
                "agree that the invoice is valid and payable (and you must pay any undisputed amount\n" +
                "included in the invoice in accordance with clause 6.4).\n" +
                "7.3 To raise a valid billing dispute, you must:\n" +
                "(a) make a good faith request to Optus to investigate the specific charges or\n" +
                "invoice, providing at the same time specific evidence which demonstrates that a\n" +
                "particular charge or invoice is incorrect; and\n" +
                "(b) make any such request to Optus within 12 months of the date of the relevant\n" +
                "invoice.\n" +
                "7.4 You may only make a claim or commence proceedings alleging that any charge or\n" +
                "invoice is incorrect, or you are entitled to a refund for overpayment, if you do so within\n" +
                "12 months of the date of invoice or overpayment.\n" +
                "7.5 If you raise a valid billing dispute, then Optus will conduct investigations which are\n" +
                "reasonably necessary and appropriate in the circumstances of the dispute. At the end of\n" +
                "these investigations, you will pay any outstanding amount (together with interest on\n" +
                "that amount calculated in accordance with clause 6.8 from the original due date for\n" +
                "\n" +
                "6\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "payment) within five Business Days. Optus may invoice you a daily professional\n" +
                "services charge to audit and validate any non-Optus analysis of the disputed amount.\n" +
                "You will pay any such professional services charge in accordance with clause 6\n" +
                "(Invoices and Payment).\n" +
                "8 TAXES\n" +
                "8.1 Charges specified in the Agreement do not include any Taxes unless otherwise stated.\n" +
                "Unless the relevant charges are stated to include Taxes, you must pay Optus an\n" +
                "additional amount equal to the Taxes on the charges, any non-monetary consideration\n" +
                "or on the supply or any component of supply made or to be made in connection with\n" +
                "the Service or the charges.\n" +
                "8.2 In some cases the Agreement may specify a charge followed by another amount in\n" +
                "parentheses or as 'without GST' and 'with GST'. In those cases, the specified charge or\n" +
                "'without GST' amount is exclusive of GST and the amount in parentheses or specified\n" +
                "as 'with GST' is:\n" +
                "(a) the charge inclusive of GST at the rate in effect at the date of the Agreement;\n" +
                "and\n" +
                "(b) included by way of information only, and its inclusion does not limit your\n" +
                "obligation under clause 8.1 to pay the GST exclusive charge together with an\n" +
                "additional amount equal to the GST at the rate applicable from time to time.\n" +
                "8.3 If you are required by law to deduct or withhold Taxes from a payment to Optus, then\n" +
                "you may make those deductions or withholdings (or both). However, you must give\n" +
                "Optus a receipt for each payment and you will increase your payment to Optus by the\n" +
                "amount necessary to ensure Optus receives the full amount which it would have\n" +
                "received if no deduction or withholding had been made.\n" +
                "8.4 If clause 8.3 applies, then Optus will on request by you apply for any credit or rebate to\n" +
                "which it may be entitled in connection with the deduction or withheld Tax. Optus will\n" +
                "refund to you any credit or rebate received, up to the amount of the increase made by\n" +
                "you under clause 8.3.\n" +
                "8.5 Optus will issue you with a valid tax invoice in respect of each taxable supply.\n" +
                "8.6 Terms used in this clause 8 which are defined in the A New Tax System (Goods and\n" +
                "Services Tax) Act 1999 (Cth) have the meaning given to them in that Act.\n" +
                "9 FINANCIAL SECURITY\n" +
                "9.1 From time to time Optus may require you to supply financial security (for example a\n" +
                "deposit or guarantee) for your obligations under the Agreement. Optus will give you\n" +
                "reasonable notice before the requirement takes effect. Optus may from time to time\n" +
                "vary the requirement for, including the amount of, this security after the Service Start\n" +
                "Date.\n" +
                "9.2 Providing financial security does not affect your obligations to Optus, including your\n" +
                "obligation to pay amounts to Optus as they become due and payable.\n" +
                "\n" +
                "7\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "9.3 Optus may apply the whole or any part of any financial security to satisfy any amount\n" +
                "you are required to pay to Optus from time to time and that is overdue. Doing so does\n" +
                "not limit Optus’ other rights under the Agreement.\n" +
                "9.4 Optus must return any remaining financial security to you after you have stopped\n" +
                "taking the Service from Optus, you have paid Optus all outstanding charges and\n" +
                "discharged all your other obligations under the Agreement and Optus is satisfied that it\n" +
                "will not be obliged to repay any amount received by it for any reason.\n" +
                "10 PRIVACY AND PERSONAL INFORMATION\n" +
                "10.1 We collect personal information directly from you, and also during our ongoing\n" +
                "relationship with you. We use it to supply products, give you the best possible service,\n" +
                "and for the other purposes described in our privacy policy. Without it, we may not be\n" +
                "able to supply products or provide the level of service you expect.\n" +
                "10.2 As explained in our privacy policy, we share personal information within the Optus\n" +
                "Group companies and with a number of other service providers and partners for these\n" +
                "purposes, some of whom may be overseas. Their locations are listed in our privacy\n" +
                "policy. If you’d like more info about our privacy practices, how to access or correct\n" +
                "our records, opt out of receiving marketing materials, or make a complaint, just head\n" +
                "over to optus.com.au/privacy.\n" +
                "11 MODIFICATIONS\n" +
                "11.1 You may request a modification (which does not include suspension or cancellation) to\n" +
                "the Service or an Individual Service at any time by giving 30 days' notice to Optus.\n" +
                "Optus may agree, in its absolute discretion, to make that modification.\n" +
                "11.2 If Optus makes the modification then:\n" +
                "(a) Optus may notify you of the revised charges for the Service; and\n" +
                "(b) if the modification is a Downgrade, you must pay a Downgrade Charge if one is\n" +
                "specified in the Service Description; and\n" +
                "(c) if the modification is not a Downgrade, you must pay the charges for making\n" +
                "the modification notified to you by Optus at the time.\n" +
                "11.3 You acknowledge that:\n" +
                "(a) a Downgrade Charge is payable each and every time you Downgrade the\n" +
                "Service or an Individual Service; and\n" +
                "(b) if you Downgrade the Service or an Individual Service in accordance with\n" +
                "paragraph 11.1:\n" +
                "(i) Optus will suffer loss in connection with the cost of network components no\n" +
                "longer required to supply the Service or Individual Service after the\n" +
                "Downgrade; and\n" +
                "\n" +
                "8\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "(ii) the Downgrade Charge represents a genuine pre-estimate of the loss Optus will\n" +
                "suffer and is not a penalty.\n" +
                "12 YOUR RIGHT TO SUSPEND OR CANCEL\n" +
                "12.1 You may ask Optus to suspend the Service or one or more Individual Services by\n" +
                "giving notice to Optus. Optus will try to do so within a reasonable period.\n" +
                "12.2 You may cancel the Service or one or more Individual Services without liability:\n" +
                "(a) at any time after the Committed Term, by giving 30 days' notice to Optus; and\n" +
                "(b) at any time by giving Optus notice if:\n" +
                "(i) all of the Individual Services making up the Service have been cancelled in\n" +
                "accordance with the Agreement;\n" +
                "(ii) Optus breaches a material term of the Agreement and that breach is not capable\n" +
                "of remedy; or\n" +
                "(iii) Optus breaches a material term of the Agreement and that breach is capable of\n" +
                "remedy but Optus does not remedy that breach within 30 days after you give\n" +
                "Optus notice requiring Optus to do so.\n" +
                "12.3 You may cancel the Service or one or more Individual Services before the end of the\n" +
                "Committed Term by giving 30 days' notice to Optus but you may be required to pay a\n" +
                "Cancellation Fee in accordance with clause 15.1.\n" +
                "13 OPTUS' RIGHT TO SUSPEND OR CANCEL\n" +
                "13.1 Optus may, without liability, cancel the Service or one or more Individual Services:\n" +
                "(a) at any time after the Committed Term by giving 30 days' notice to you; or\n" +
                "(b) at any time prior to the Service Start Date, if Optus reasonably determines that\n" +
                "it is not technically or operationally feasible or commercially viable to supply\n" +
                "the Service or an Individual Service to you.\n" +
                "13.2 Optus may, without liability, immediately cancel the Service or one or more Individual\n" +
                "Services or suspend or Downgrade the Service or one or more Individual Services at\n" +
                "any time if:\n" +
                "(a) there is an emergency;\n" +
                "(b) doing so is necessary to allow Optus or a Supplier to repair, maintain or service\n" +
                "any part of the Optus Network or a Supplier Network used to supply the\n" +
                "Service or the Individual Service;\n" +
                "(c) you breach clause 2 (Use of the Service) or your obligations relating to the use\n" +
                "of the Service set out in the Service Description, otherwise misuse either the\n" +
                "Service or an Individual Service or breach clause 3.5;\n" +
                "\n" +
                "9\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "(d) you fail to provide financial security required under the Agreement;\n" +
                "(e) Optus reasonably suspects fraud by you or any other person in connection with\n" +
                "either the Service or an Individual Service;\n" +
                "(f) Optus reasonably believes there has been an unusually high use of either the\n" +
                "Service or an Individual Service, or if you significantly change any traffic\n" +
                "profile or forecast given to Optus;\n" +
                "(g) any amount owing to Optus or any Optus Group Company is not paid by its\n" +
                "due date (as specified in the relevant invoice or notified by Optus), Optus gives\n" +
                "you notice requiring payment of that amount (which Optus may not give in\n" +
                "respect of an amount which is validly disputed in accordance with clause 7\n" +
                "(Billing Disputes) until after Optus has completed the investigations referred to\n" +
                "in clause 7.5) and you fail to pay that amount in full within five Business Days\n" +
                "after Optus gives you that notice;\n" +
                "(h) you breach a material term of the Agreement (other than a breach which\n" +
                "separately gives rise to rights under this clause 13.2) and that breach is not\n" +
                "capable of remedy;\n" +
                "(i) you breach a material term of the Agreement (other than a breach which\n" +
                "separately gives rise to rights under this clause 13.2) and that breach is capable\n" +
                "of remedy you do not remedy that breach within 30 days after Optus gives you\n" +
                "notice requiring you to do so;\n" +
                "(j) Optus or an Optus Group Company is entitled to (or does) suspend, terminate\n" +
                "or cancel any other service supplied to you, or is entitled to (or does) terminate\n" +
                "any other agreement with you;\n" +
                "(k) Optus is required to do so to comply with an order, instruction or request of a\n" +
                "Regulator, an emergency services organisation or any other competent\n" +
                "authority;\n" +
                "(l) Optus is issued with a Competition Notice under Part XIB of the Competition\n" +
                "and Consumer Act in respect of the Service, or the Service is declared under\n" +
                "Part XIC of the Competition and Consumer Act;\n" +
                "(m) problems are experienced interconnecting the Optus Network with any\n" +
                "Supplier Network;\n" +
                "(n) a Supplier terminates its agreement with Optus, or ceases to supply services to\n" +
                "Optus, and Optus is not able to provide either the Service or an Individual\n" +
                "Service using services supplied to Optus by an alternate Supplier on terms\n" +
                "reasonably acceptable to Optus;\n" +
                "(o) you suffer an Insolvency Event;\n" +
                "(p) you die or, if you are a partnership, the partnership is dissolved or an\n" +
                "application is made to dissolve the partnership; or\n" +
                "\n" +
                "10\n" +
                "Optus General Terms (SFOA) 3 April 2014\n" +
                "(q) Optus is otherwise entitled to do so under this Agreement.\n" +
                "13.3 Optus may also cancel the Service or one or more Individual Services if:\n" +
                "(a) the Service or that Individual Service is suspended for more than 14 days; or\n" +
                "(b) any Intervening Event prevents the supply of the Service or that Individual\n" +
                "Service in accordance with the Agreement for more than 14 days.\n" +
                "13.4 Optus may exercise its rights under clause 13.2 immediately by giving notice to you,\n" +
                "unless otherwise set out in the Agreement (including in any appendix to the Service\n" +
                "Description). However:\n" +
                "(a) in an emergency Optus may be precluded from giving notice; and\n" +
                "(b) if Optus intends to exercise its rights under clause 13.2 as a result of\n" +
                "circumstances referred to in paragraphs (b), (k), (l), (m) or (n) of that clause,\n" +
                "then Optus will usually try to give you reasonable notice before cancelling the\n" +
                "Service or one or more Individual Services or suspending the Service or one or\n" +
                "more Individual Services.\n" +
                "13.5 If Optus suspends the Service or an Individual Service under this clause 13, then Optus\n" +
                "may later cancel the Service or that Individual Service for the same or a different\n" +
                "reason.\n" +
                "14 CONSEQUENCES OF SUSPENSION\n" +
                "14.1 If the Service or an Individual Service is suspended in accordance with the Agreement,\n" +
                "then you will not be liable to pay usage based charges for the Service or the Individual\n" +
                "Service while it is suspended but otherwise you must pay all charges arising before,\n" +
                "during and after suspension in accordance with the Agreement.\n" +
                "14.2 If you ask Optus to do so, Optus will stop the suspension of the Service or Individual\n" +
                "Service as soon as reasonably possible after the reason for that suspension has stopped.\n" +
                "14.3 If Optus reactivates or reinstates the Service or an Individual Service after it has been\n" +
                "suspended, you may have to pay Optus a reactivation charge.\n" +
                "15 CONSEQUENCES OF CANCELLATION\n" +
                "15.1 If the Service or an Individual Service is cancelled in accordance with the Agreement\n" +
                "(other than in accordance with clause 12.2):\n" +
                "(a) before the Service Start Date, then you must pay to Optus all infrastructure and\n" +
                "installation costs incurred by Optus in connection with preparation for the\n" +
                "provision of the Service or the Individual Service; and\n" +
                "(b) during the Committed Term, then you must pay to Optus the Cancellation Fee.\n" +
                "15.2 If an Individual Service is cancelled in accordance with the Agreement, then Optus may\n" +
                "notify you of the revised charges for the Service.\n" +
                "\n";
        TermsAndCondition.setText(summary);
    }
}
